# Sudoku-MK-something

An interactive CLI Sudoku engine with file reading/writing, an optimized solver (under development), and a simple brute-force one.

This engine can:
1. Solve
2. Play
3. Generate
4. Validate

## Features
- Two solvers: one optimized with bitmasks and basic heuristics, and one straightforward barebones solver
- CLI menus with basic error handling
- Puzzle generation for sizes 4×4, 9×9, and 16×16 with basic uniqueness checking
- Two game modes (Rush, Zen)
- Basic file handling using `FileWriter` and `FileReader`
- Procedural puzzle generation with optional seeding

## Structure

- `Board`: Handles the board’s attributes and some helper methods  
- `Solver`: The optimized solver  
- `SolverBrute`: The unoptimized solver  
- `Gen`: Generation logic  
- `FileManager`: Basic file I/O  
- `UserInterface`: Most CLI I/O  
- `GameLoop`: The game logic and flow  
- `GameMode`: The two game modes  
- `TimeControl`: Time-related helper methods  
- `Main`: The starting point

## TODO / In Progress
- [ ] Improve puzzle uniqueness checks
- [ ] Improvement of the cell selection method
- [ ] A better testing class


## Contributing

Contributions, ideas, or bug reports are welcome! Please open an issue or pull request.

## License
This project is licensed under the MIT License.

## Author
**ammar** (phanta1sa)
