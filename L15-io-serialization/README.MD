# IO and serialization

## Description

Handling input and output are common tasks for Java programmers. In this tutorial, we'll look at the original java.io (
IO) libraries and the newer java.nio (NIO) libraries and how they differ when communicating across a network.

## EXAMPLES

1. Package io:
    - Read and write file using IO (input and output stream)
   - Read and write file using FileReader and FileWriter
   - Serializable and deserializable Objects
2. Package nio. Demonstrate how to work with Files and Paths
3. Package json:
   - Demonstrate how to serialize, deserialize own objects and instance of classes.
4. Package protobuf (this example is not full!):
   - Protobuf is well-known language-agnostic binary data format. We can define a file with a protocol and next, using
     that protocol, we can generate code in languages like Java, C++, C#, Go, or Python.
   - Installation:
     - Add dependencies to gradle.build
     - Add proto file. Where we describe format of our message.
     - Add gradle plugin




