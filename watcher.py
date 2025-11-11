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
JAVA_SRC_DIR = "src/main/java"
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
        repo.git.add(all=True)
        commit_message = f"🤖 Auto-fix, docs, and tests for {os.path.basename(file_path)}"
        repo.index.commit(commit_message)
        origin = repo.remote(name="origin")
        origin.push()
        print(f"✅ Auto committed & pushed changes for: {file_path}")

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