# Glossary
**Overview**
This project is focused on creating a glossary generator. The generator is designed to produce a series of HTML files that comprise a glossary. Each term is indexed and linked to its definition, offering an intuitive and user-friendly digital reference tool.

**Features**
HTML Output: Generates a set of HTML files - an index of terms and individual pages for each term and its definition.
Interactive Index: The top-level index lists all glossary terms. Clicking a term redirects to its specific page with the definition.
Cross-Linking: Terms appearing within definitions link to their respective pages, enhancing navigation.
Alphabetical Ordering: Terms are listed alphabetically in the index for easy lookup.
Styling: In each term's page, the term is styled in red boldface italics for emphasis.
Batch Processing: The program creates the entire glossary in a batch process from a single input file.
User Input for File Paths: Users specify the input file name and output folder path, which the program accepts without alterations or assumptions.

**Input File Format**
The input file should have a specific format: each term is followed by its definition, separated by an empty line.
Example format:
Term1
Definition of Term1...

Term2
Definition of Term2...

**Usage**
Prepare the Input File: Ensure your glossary terms and definitions are in the required format.
Run the Program: Upon execution, the program will prompt for the input file name and output folder path.
Enter File and Folder Names: Provide the complete path for the input file and the output folder. Ensure the output folder exists.
Access the Glossary: Navigate to the specified output folder and open index.html to view the glossary.

**Requirements**
The output folder must exist prior to running the program; it will not create the folder.
Input must adhere to the specified format for accurate processing.
The program assumes the input is valid and will not perform error checking.

**Example Paths**
Input file: "data/terms.txt"
Output folder: "data"
