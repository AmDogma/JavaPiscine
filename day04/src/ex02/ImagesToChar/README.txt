#delete target from last session
rm -rf target lib

#Create target directory
mkdir target lib

#Download external libraries
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JColor-5.3.0.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.3.0/JColor-5.3.0.jar

#No need
rm -rf META-INF

#Make class objects
javac -d target -cp lib/JColor-5.3.0.jar:lib/jcommander-1.82.jar:. src/java/edu/school21/printer/*/*.java 

#Copy directory
cp -R src/resources target/.

#extract jar libraries
unzip lib/jcommander-1.82.jar -d ./target
rm -rf  target/META-INF
unzip lib/JColor-5.3.0.jar  -d ./target
#jar xvf lib/jcommander-1.82.jar -C
#jar xvf lib/JColor-5.3.0.jar -C 


#Make jar file
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target .

#Run
java -jar target/images-to-chars-printer.jar --white=RED --black=BLUE