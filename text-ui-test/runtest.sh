#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/uhg/uhgbot -Xlint:none -d ../bin $(find ../src/main/java/uhg/uhgbot -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin uhg.uhgbot.UhgBot < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
echo "Comparing output against expected output..."
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
    # delete saved data if exists
    if [ -e "./data" ]
    then
        rm -r data
    fi
    exit 1
fi

# compare generated uhgbot.txt to expected uhgbot.txt
echo "Comparing generated uhgbot.txt against expected uhgbot.txt..."
diff data/uhgbot.txt expected-uhgbot.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    # delete saved data if exists
    if [ -e "./data" ]
    then
        rm -r data
    fi
    exit 0
else
    echo "Test result: FAILED"
    # delete saved data if exists
    if [ -e "./data" ]
    then
        rm -r data
    fi
    exit 1
fi