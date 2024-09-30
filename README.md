# The solution:

## UX info
- Displays a list of books provided by the API endpoint
- The list can use 1, 2 or 3 columns, depending on the screen width
- Each list item has a button that opens the book's listing on Amazon
- The app handles screen rotation (tested on a phone, tablet and a foldable device)
- In case of a network error or a timeout, the app displays a button to try again
- In the accessibility mode, the screen reader announces each list item (author, title, description) when the user swipes the screen

## Tech info
- Implements MVP, single activity + main fragment
- ViewBinding: for the main fragment and the RecyclerView adapter
- Dagger: to inject the repo into the use case, and the use case into the main fragment, so the use case could be passed to the presenter
- Retrofit: to access the API endpoint
- Glide: to load the book cover images in the adapter
- Provides unit tests that test the MVP presenter's success and failure paths
- Bumps the lib versions to the most recent available. Removes the unused dependencies such as Rx.

----

# The Challenge:

The challenge is to create a simple Android app that exercises a REST-ful API. The API endpoint `https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=KoRB4K5LRHygfjCL2AH6iQ7NeUqDAGAB&offset=0` returns a JSON object which is a list of different books published by the New York Times. 

Using this endpoint, show a list of these items, with each row displaying at least the following data:

- Image
- Title
- Description 

### Technical Instructions:
- MVP architecture, no ViewModel
- XML Layouts (no Compose)
- Demonstrate use of Dagger, Retrofit and Glide (for the images)
- No database needed
- Feel free to make any assumptions you want along the way or use any third party libraries as needed and document why you used them.

### General Instructions:
- This isn't a visual design exercise. For example, if you set random background colors to clearly differentiate the views when debugging, pick Comic Sans or Papyrus, we won't hold that against you. Well, maybe a little bit if you use Comic Sans :)
- This is also most of the code you'll be showing us – don't underestimate the difficulty of the task, or the importance of this exercise in our process, and rush your PR. Put up your best professional game.
- This isn't just about handling the happy path. Think slow network (or no network at all), supporting different device sizes, ease of build and run of the project. If we can't check out and click the run button in Android Studio, you're off to a bad start (we've had PRs without a graddle for instance).
- Explanations on any choice you've made are welcome.
- We appreciate there's a lot that is asked in this exercise. If you need more time, feel free to ask. If you need to de-prioritize something, apply the same judgement you would on a professional project, argument your decision. 

Bonus Points:
  - Unit tests
