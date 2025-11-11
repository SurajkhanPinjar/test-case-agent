import subprocess
import os

def run_ollama(model: str, prompt: str) -> str:
    """
    Executes Ollama model and returns clean text output.
    Handles large prompts gracefully.
    """
    try:
        result = subprocess.run(
            ["ollama", "run", model, prompt],
            capture_output=True, text=True, check=True
        )
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        print(f"❌ Ollama error: {e.stderr}")
        return "Error: Ollama execution failed"


def save_generated_tests(java_file_path: str, test_code: str):
    """
    Saves generated test cases inside:
    src/main/java/com/example/reviewed/
    Creates folder automatically if missing.
    """
    reviewed_dir = "src/main/java/com/example/reviewed"
    os.makedirs(reviewed_dir, exist_ok=True)

    filename = os.path.basename(java_file_path).replace(".java", "Test.java")
    output_path = os.path.join(reviewed_dir, filename)

    with open(output_path, "w") as f:
        f.write(test_code)

    print(f"💾 Saved generated test cases to: {output_path}")
    return output_path


def generate_test_cases(java_file_path: str):
    """
    AI Agent: Test Case Generator using Ollama (Mistral model)
    Analyzes Java source code and generates JUnit test cases with Mockito.
    """
    if not os.path.exists(java_file_path):
        print(f"❌ File not found: {java_file_path}")
        return

    with open(java_file_path, "r") as f:
        java_source = f.read()

    prompt = f"""
You are a professional AI Test Case Generator Agent.

Task:
- Analyze the given Java class
- Generate complete JUnit test cases using Mockito where needed
- Include edge cases, null checks, and exception scenarios
- Output only valid Java code for the test class

Java Source Code:
{java_source[:5000]}
    """

    print(f"🧪 Generating test cases for: {java_file_path}")
    generated_tests = run_ollama("mistral", prompt)
    reviewed_path = save_generated_tests(java_file_path, generated_tests)
    print(f"✅ Test case generation complete: {reviewed_path}")


if __name__ == "__main__":
    # Example usage (only scanning service files)
    generate_test_cases("src/main/java/com/example/service/UserService.java")
    generate_test_cases("src/main/java/com/example/service/User.java")