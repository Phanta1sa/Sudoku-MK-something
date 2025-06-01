# Sudoku-MK-something
An interactive CLI Sudoku engine with file reading/writing an optizmized solver(underdevelopment still), and a simple brute force one.

this engine should be able to 
1. Solve
2. Play
3. Gen
4. Validate

-Features
  - 2 solvers, one optimized with bitmasks and some other simple things, the other is a straightforward barebones one
  - CLI menus with basic error handling
  - Puzzle generation for sizes 4, 9, and 16 with basic uniqueness checking.
  - 2 game modes (rush, zen)
  - basic file handling using fileWrites and fileReaders.

 -Structure
   src/
     1-Board: handling the board’s attributes and some helper methods
     2-Solver: the optimized solver
     3-SolverBrute: the unoptimized solver
     4-Gen: generation logic
     5-FileManager: basic file I/O
     6-UserInterface: most CLI I/O
     7-GameLoop: the game logic and flow 
     8-GameMode: the 2 game modes
     9-TimeControl:time-relate helper methods 
     10 -Main: the starting point

 -License:
   This project is licensed under the MIT License.

  -Author : **ammar** (phanta1sa)
     
 
