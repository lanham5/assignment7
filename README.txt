About:
The "Cheaters!" program serves to accomplish basic plagiarism detection. Given a set of documents, it will examine the number of shared sequences two documents have between each other. The program performs this check for all pairings in order to give a complete view of potential plagiarism across the entire directory of documents. Depending on user interest, this detector can use short or long fixed-length sequences with which to evaluate similarity. This means that the program can evaluate with varying degrees of granularity.


How to use:
To use "Cheaters!", the user will utilize the command line. However, before entering any text, the user must place the files of interest in a single directory somewhere on the computer. The folder should contain only text files (these files are the documents which are to be evaluated). 

Once this is completed, the user will input some variation of the following command: "java cheaters path/to/files 6 300". The first two keywords are constant and will lead any invocation. In place of "path/to/files", the user will place the path to the directory that contains the document set that needs to be examined for plagiarism. The first number following the path sets the length of sequences. Thus, entering some number n means that the program will scan the documents looking for common n-length sequences. The second number following the path sets the minimum number of common sequences two documents must have to be output.

Upon entering this command, the program will return a list of documents that meets the similarity requirement specified by the user. Additionally, it will output a graph that presents a visual representation of similarity within the data set.


What works:
This program performs best when given less than 50 documents that are 1,000 words or less. These are very general outlines and do not denote any limitations in the computational ability of the program. However, when considering what data set to use, it is important to remember that run-time will increase significantly with many and / or large documents. Ultimately, the total number of words in the data set will be the determinant of run-time.


What doesn't work / bugs:
Other than practical limitations with regard to data input size, this program should input per user specification without error. Unfortunately, due to the variety of data that might be fed into the program, labelling document similarity as "suspicious" presents a challenge. Using similarity relative to the size of the documents generally works but may be less than ideal when presented with longer documents. 

Additionally, number of common sequences is an imperfect metric for evaluating plagiarism. A document may have only one given "hot zone" that is plagiarized in an otherwise lengthy file yet not meet a similarity thresshold. Despite showing obvious signs of plagiarism, it might not be detected because it is localized. However, with alterations and additions to have a more holistic description of plagiarism, this too could be mitigated.