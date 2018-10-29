# PdfRedererRecyclerView
A LaatonWalaBhoot creation...

A sample implementation of the PDFRenderer class introduced in API level 21. Instead of paging a simple Recycler view implementation has been provided with Pinch zooming. This version uses Coroutines to read and load local PDF from the assets folder. You are free to add your own exception handling, inside the Lifecycle observer class. 

# Why Coroutines?
I wanted to download and view the file from origin at the start but did not get the time to implement a download manager along with progress updation. So anyone interested is free to submit a pull request. You can implement your own solution using Asynctask or RxCallbacks as well.

# Testing
Not been thouroughly tested with heavy PDF's and in tab layouts containg fragments. For a legacy implementaion, do checkout Oleg Gallegos PDFViewPager. The Bitmap arraylist idea needs to be rethought of for better memory managment in case of bigger files. 

# Food for thought
- Add a loading bar
- Page jumping 
- Tap zooming
- Cross Origin PDF Viewing
- Display controls
