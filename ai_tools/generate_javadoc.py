import subprocess

def generate_javadoc(java_file_path):
    """
    AI Agent: JavaDoc Generator using Ollama (Mistral model)
    Reads Java source code and adds professional JavaDoc comments.
    """

    # 1️⃣ Read your Java source code file
    with open(java_file_path, "r") as f:
        java_source = f.read()

    # 2️⃣ Prepare the prompt
    prompt = f"""
    You are a senior Java documentation engineer.
    Read the following Java class and generate detailed JavaDoc comments for:
    - All classes, constructors, and public methods
    - Include parameter (@param) and return (@return) descriptions
    - Keep original code structure unchanged
    - Output only valid Java code with comments added

    Java Source Code:
    {java_source[:5000]}
    """

    # 3️⃣ Run Mistral model
    result = subprocess.run(
        ["ollama", "run", "mistral", prompt],
        capture_output=True, text=True
    )

    # 4️⃣ Save the new JavaDoc’d code
    documented_code = result.stdout.strip()
    output_path = java_file_path.replace(".java", "_JavaDoc.java")

    with open(output_path, "w") as f:
        f.write(documented_code)

    print(f"\n💾 Saved JavaDoc-enhanced file to: {output_path}")


if __name__ == "__main__":
    generate_javadoc("../src/main/java/com/example/service/UserService.java")
    generate_javadoc("../src/main/java/com/example/service/User.java")