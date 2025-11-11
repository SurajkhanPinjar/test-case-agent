import subprocess
import os

def run_ollama(model: str, prompt: str) -> str:
    """
    Helper function to execute Ollama model efficiently.
    Handles large prompts gracefully and returns clean text output.
    """
    result = subprocess.run(
        ["ollama", "run", model, prompt],
        capture_output=True, text=True
    )
    return result.stdout.strip()


def find_and_fix_bugs(java_file_path: str):
    """
    AI Agent: Java Bug Finder & Reviewer using Ollama (Mistral model)
    ✅ Reads a Java file
    ✅ Identifies potential bugs and code smells
    ✅ Suggests fixes and optimized code
    ✅ Generates review comments and improved code version
    """

    if not os.path.exists(java_file_path):
        print(f"❌ File not found: {java_file_path}")
        return

    # 1️⃣ Read Java source
    with open(java_file_path, "r") as f:
        java_source = f.read()

    # 2️⃣ AI Prompt for Review + Bug Fix
    prompt = f"""
    You are a senior Java backend engineer and code reviewer.
    Analyze the following Java source code for:
    - Bugs and logical errors
    - Code smells or violations of SOLID principles
    - Missing null checks, concurrency issues, or performance bottlenecks
    - Suggest clear FIXES
    - Then provide an OPTIMIZED version of the full code below.
    Keep the original class structure intact.

    Your output format must be:
    🧩 REVIEW COMMENTS:
    (bullet points of found issues and fixes)
    
    🚀 FIXED & OPTIMIZED CODE:
    (provide full corrected Java source)

    Java Source Code:
    {java_source[:5000]}
    """

    # 3️⃣ Run the model
    output = run_ollama("mistral", prompt)

    # 4️⃣ Save results neatly
    output_path = java_file_path.replace(".java", "_BugReviewed.java")
    with open(output_path, "w") as f:
        f.write(output)

    print(f"\n✅ Bug review and fixes saved to: {output_path}")
    print("🔍 Check the file for AI-generated review comments and optimized code.")


if __name__ == "__main__":
    # Example file (update this path as needed)
    find_and_fix_bugs("src/main/java/com/example/service/UserService.java")
    find_and_fix_bugs("src/main/java/com/example/service/User.java")