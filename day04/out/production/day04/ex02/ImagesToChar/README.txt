#delete target from last session
rm -rf target lib

#Create target directory
mkdir target lib

#Download external libraries
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar
curl -o lib/JColor-5.3.0.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.3.0/JColor-5.3.0.jar

#Make class objects
javac -d target src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/Convert.java

#Copy directory
cp -R src/resources target/.

#Make jar file
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target .
#chmod u+x target/images-to-chars-printer.jar

#Run
java -jar target/images-to-chars-printer.jar --white=0 --black=1