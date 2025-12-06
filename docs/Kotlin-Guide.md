Kotlin Guide — repository conventions

This short guide explains the conventions we use in this repo.

Project layout
- `src/kt/` — Kotlin source files organized by topic. Example:
  - `src/kt/binarytree/TreeNode.kt`
  - `src/kt/recursion/PowerSet.kt`

File and naming conventions
- Use `PascalCase` for Kotlin file names that contain classes or single solutions.
- Keep single-responsibility files. If a file contains multiple utility functions, group them by topic.

Running solutions
- Recommended: Open the repo in IntelliJ IDEA and run individual `main` functions.
- Alternatively: compile single files with `kotlinc` and run with `kotlin`:
  - `kotlinc src/kt/<topic>/<File>.kt -d out.jar`
  - `kotlin -classpath out.jar <FullyQualifiedMainClass>`

Testing
- We don't enforce a test framework here. You may add simple `main` drivers or a `test/` directory with a preferred testing framework.

Style
- Prefer clear, readable code over clever micro-optimizations. Add comments where helpful.
