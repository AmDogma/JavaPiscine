
#delete target from last session
rm -rf target

#Create target directory
mkdir target

#Make class objects
javac -d target src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/Convert.java

#Run change to your path "/Users/mdewayne/IdeaProjects/day04/src/ex01/ImagesToChar/src/java/resources/it.bmp"
java -cp target edu.school21.printer.app.Program --white=0 --black=1