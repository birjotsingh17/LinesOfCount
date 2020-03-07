# LinesOfCount

While running jar file, we have to give the path of the project for which we want the lineCount.
Check the screenshot to be more clear how to run..

This project fulfill all the five counts 

In the starting I took the input for fullPath variable using args[0]

1. Total number of java files:
	for this I used walk method that collects all the files with .java format

2. Unique Java files:	
	To find all the unique java files, I calculated the hashvalue of every java file and stored them in a hashmap (hashvalue as a key and file as value).
	unique files = size of hashmap.
	
3. Blank Lines:
	I used hash map iterator to pass from all the files in the hashmap. while passing from each and every line if the is null, the variable blank line is incremented.

4. Comment Lines: 
	There are 2 types of comments: single and multi line comments
	I took one variable as ini which is a substring of starting 2 character of the line
	a) for single line, if ini is equal to "//" then commentLines variable is incremented.
	b) for multiline, if ini is equal to "/*" then commentLines variable is incremented till the line has "*/" in it.
	
5. Code Lines:
	if the line is not a Blank line nor the comment line, then it is a code line so codeLines variable is incremented.

At last, these all are printed in a single line in the format below ::
totalNumberOfJavaFiles - uniqueFiles - blankLines - commentLines - codeLines

   
