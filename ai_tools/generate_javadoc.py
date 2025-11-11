import subprocess
import os

def run_ollama(model: str, prompt: str) -> str:
    """
    Executes Ollama model efficiently and returns clean text output.
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


def save_documented_file(java_file_path: str, documented_code: str):
    """
    Saves the generated JavaDoc-enhanced file inside a /reviewed subfolder.
    Creates folder automatically if missing.
    """
    base_dir = os.path.dirname(java_file_path)
    reviewed_dir = os.path.join(base_dir, "reviewed")
    os.makedirs(reviewed_dir, exist_ok=True)

    filename = os.path.basename(java_file_path).replace(".java", "_JavaDoc.java")
    output_path = os.path.join(reviewed_dir, filename)

    with open(output_path, "w") as f:
        f.write(documented_code)

    print(f"💾 Saved JavaDoc-enhanced file to: {output_path}")
    return output_path


def generate_javadoc(java_file_path: str):
    """
    AI Agent: JavaDoc Generator using Ollama (Mistral model)
    Reads Java source code and generates professional JavaDoc comments.
    """
    if not os.path.exists(java_file_path):
        print(f"❌ File not found: {java_file_path}")
        return

    with open(java_file_path, "r") as f:
        java_source = f.read()

    prompt = f"""
You are a senior Java documentation engineer.
Read the following Java class and generate detailed JavaDoc comments for:
- All classes, constructors, and public methods
- Include @param and @return descriptions
- Keep the original code structure unchanged
- Output only valid Java code with comments added

Java Source Code:
{java_source[:5000]}
    """

    print(f"📝 Generating JavaDocs for: {java_file_path}")
    documented_code = run_ollama("mistral", prompt)
    reviewed_path = save_documented_file(java_file_path, documented_code)
    print(f"✅ JavaDoc generation complete: {reviewed_path}")


if __name__ == "__main__":
    # Example usage — change as needed
    generate_javadoc("src/main/java/com/example/service/UserService.java")
    generate_javadoc("src/main/java/com/example/service/User.java")