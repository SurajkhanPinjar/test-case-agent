import subprocess
import os

def run_ollama(model: str, prompt: str) -> str:
    """
    Executes an Ollama model efficiently and returns clean text output.
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


def save_reviewed_file(java_file_path: str, output: str):
    """
    Saves reviewed and optimized Java code inside:
    src/main/java/com/example/reviewed/
    """
    reviewed_dir = "src/main/java/com/example/reviewed"
    os.makedirs(reviewed_dir, exist_ok=True)

    filename = os.path.basename(java_file_path).replace(".java", "_BugReviewed.java")
    output_path = os.path.join(reviewed_dir, filename)

    with open(output_path, "w") as f:
        f.write(output)

    print(f"💾 Saved reviewed file to: {output_path}")
    return output_path


def find_and_fix_bugs(java_file_path: str):
    """
    AI Agent: Java Bug Finder & Reviewer using Ollama (Mistral model)
    ✅ Reads a Java file
    ✅ Identifies potential bugs & code smells
    ✅ Suggests fixes and optimized code
    ✅ Saves result neatly inside src/main/java/com/example/reviewed/
    """
    if not os.path.exists(java_file_path):
        print(f"❌ File not found: {java_file_path}")
        return

    with open(java_file_path, "r") as f:
        java_source = f.read()

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

    print(f"🔍 Reviewing: {java_file_path}")
    output = run_ollama("mistral", prompt)
    reviewed_path = save_reviewed_file(java_file_path, output)
    print(f"✅ Bug review complete: {reviewed_path}")


if __name__ == "__main__":
    # Example usage — only scans /service folder files
    find_and_fix_bugs("src/main/java/com/example/service/UserService.java")
    find_and_fix_bugs("src/main/java/com/example/service/User.java")