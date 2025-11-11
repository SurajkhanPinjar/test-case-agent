import subprocess

def generate_test_cases(java_file_path):
    """
    AI Agent: Test Case Generator using Ollama (Mistral model)
    Reads Java source code and generates JUnit test cases with Mockito.
    """

    # 1️⃣ Read your Java source code file
    with open(java_file_path, "r") as f:
        java_source = f.read()

    # 2️⃣ Prepare the prompt (you give the model your Java code)
    prompt = f"""
    You are a professional AI Test Case Generator Agent.

    Task:
    - Analyze the given Java class
    - Generate complete JUnit test cases using Mockito where needed
    - Include edge cases, null checks, and exception scenarios
    - Output only valid Java code for the test class

    Java Source Code:
    {java_source[:5000]}   # first 5000 chars of the file to avoid overflow
    """

    # 3️⃣ Run the Mistral model via Ollama
    result = subprocess.run(
        ["ollama", "run", "mistral", prompt],
        capture_output=True, text=True
    )

    # 4️⃣ Get the generated test code
    generated_tests = result.stdout.strip()

    # 5️⃣ Print or save to file
    print("\n✅ Generated Test Cases:\n")
    print(generated_tests)

    # Optional: save to a .java file automatically
    output_path = java_file_path.replace(".java", "Test.java")
    with open(output_path, "w") as f:
        f.write(generated_tests)

    print(f"\n💾 Saved test cases to: {output_path}")


if __name__ == "__main__":
    # Example usage (update path to your file)
    generate_test_cases("src/main/java/com/example/service/UserService.java")
    generate_test_cases("src/main/java/com/example/service/User.java")