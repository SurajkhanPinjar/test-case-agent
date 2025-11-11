import time
import subprocess
import os
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
from ai_tools.bug_finder_ai import find_and_fix_bugs
from ai_tools.generate_javadoc import generate_javadoc
from ai_tools.generate_tests_ollama import generate_test_cases
from git import Repo

# 🧠 CONFIG
JAVA_SRC_DIR = "src/main/java/com/example/service/"
GIT_REPO_PATH = "."   # path to your git repo
repo = Repo(GIT_REPO_PATH)

class CodeChangeHandler(FileSystemEventHandler):
    def on_modified(self, event):
        if event.src_path.endswith(".java"):
            print(f"\n🧩 Detected change in: {event.src_path}")

            # Step 1️⃣ Bug review & fix
            find_and_fix_bugs(event.src_path)

            # Step 2️⃣ Generate JavaDocs
            generate_javadoc(event.src_path)

            # Step 3️⃣ Generate Tests
            generate_test_cases(event.src_path)

            # Step 4️⃣ Auto Git Commit
            self.commit_changes(event.src_path)

    def commit_changes(self, file_path):
        """
        Auto-commit and push file changes to GitHub safely.
        Handles authentication, network, and branch fallback gracefully.
        """

        try:
            # 1️⃣ Stage all modified files
            repo.git.add(all=True)

            # 2️⃣ Commit changes with clear message
            commit_message = f"🤖 Auto-fix, docs, and tests for {os.path.basename(file_path)}"
            repo.index.commit(commit_message)
            print(f"✅ Committed changes for: {file_path}")

            # 3️⃣ Identify the remote repo
            try:
                origin = repo.remote(name="origin")
            except ValueError:
                print("⚠️ No remote named 'origin' found. Adding one...")
                repo.create_remote("origin", "https://github.com/SurajkhanPinjar/test-case-agent.git")
                origin = repo.remote(name="origin")

            # 4️⃣ Ensure on correct branch (main or ai-autofix)
            current_branch = repo.active_branch.name
            print(f"📂 Current branch: {current_branch}")

            if current_branch not in ["main", "ai-autofix"]:
                print("🔀 Switching to ai-autofix branch...")
                repo.git.checkout("-B", "ai-autofix")

            # 5️⃣ Try pushing to remote
            try:
                origin.push()
                print("🚀 Changes pushed successfully!")
            except Exception as push_err:
                print(f"⚠️ Git push failed — {push_err}")
                print("💡 Tip: check your Git credentials or network connection.")

        except Exception as e:
            print(f"❌ Commit operation failed: {e}")

if __name__ == "__main__":
    print(f"🚀 Watching directory: {JAVA_SRC_DIR}")
    event_handler = CodeChangeHandler()
    observer = Observer()
    observer.schedule(event_handler, JAVA_SRC_DIR, recursive=True)
    observer.start()

    try:
        while True:
            time.sleep(2)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()