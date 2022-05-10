#delete target from last session
rm -rf target

#Create target directory
mkdir target

#Make class objects
javac -d target src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/Convert.java

#Make jar file
jar cvmf ./target/images-to-chars-printer.jar src/manifest.txt -C target edu/ -C src/ resources

#Run
java -jar target/images-to-chars-printer.jar --white=0 --black=1