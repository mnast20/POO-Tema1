# POO-Tema1
Implementation of a simplified video streaming platform that keeps a database of actors, users, show and movies information. The Input data is extracted from a JSON file and put inside the Database and then the required actions (queries,recommendations, user commands) are processed and their results are displayed in an output JSON file.

## Main Classes:
# User: 
The User class keeps user's subscription, username, history, list of favourite videos, as well as his number of given ratings and a map with a video name as key and a list of integers as values. This map's main focus is rating shows and keeping a list of rated seasons. In case a movie is rated, the title will be put inside the map with an empty List because a movie has no seasons. However, when rating a show's season, the system first checks if that season was previously rated and if not, the season number is added to the list in the Hashmap.

# Actor:
The Actor class holds the actor's name, career description, filmography and a map keeping every number for received awards. The only difference from the ActorInputDta class is that this class retains the number of overall awards in order to not always calculate that value whenever sorting actors by awards.

# Video:
Class extended by the Movie and Show entities, keeps a video's title, list of genres, cast, duration, rating and year of making, as well as number of views and number of appearances within users' favourites list.
   
- Movie: class storing a movie's duration and list of given ratings from all users that will be used in finding movie's average rating.

- Show: class containing a show's number of seasons and a list of seasons. Every season will also have a list of ratings of their own, used for calculating show's average rating

## Database class:
The Database contains all the users, videos, actors.The class is declared as a lazy Singleton in order to be able to access the Database instance and its contents from every other class. At the end of the program, the Database will clear itself, being declared as empty.

## Action classes:
# SolveAction:
This class contains a method that determines what type of action is requested to be executed and returns the action's result

# SolveCommand:
This class is meant to determine the command type and execute it

# SolveQuery:
This class is meant to determine the query type and execute it

# SolveQRecommendation: This class is meant to determine the recommendation type and execute it

# Command:
There are three types of commands: view, favourite and add rating.  
If the user views a video, then his history gets updated and the video's overall views increase.  
The favourite command is similar, but only if the video isn't present in the user's favourites list, will it be updated and video's overall number of favourites will increase.  
However, if the command is for adding a rating, it is first checked whether the rated video is a show or a movie. In a movie's case, if it wasn't previously rated, the new rating will be added to the movie's rating list and the movie's average rating will be recalculated. As for shows, the user can only rate one season at a time. If the season has not received any rating from the user, the new value of show's average rating will be computed based on season's updated ratings list.

# Recommendation:
There are 5 types of recommendations: standard and best unseen (for all users) and popular, favourite and search (for premium users).  
The Standard Recommendation traverses the list of all videos in the database until the first video that was not watched by user is found.  
The Best Unseen Recommendation sorts all videos in descending order by average rating and traverses the sorted list until the first video that was not watched by user is found.  
The Popular Recommendation sorts genres in descending order by number of views and traverses the list of all videos for each genre in the sorted list of genres until the first video that was not watched by user is found.  
The Favourite Recommendation sorts all videos in descending order by number of appearances in users' favourites lists and traverses the sorted list until the first video that was not watched by user is found.  
The Search Recommendation sorts all videos in a required genre in ascending order by average rating and then, as a second criteria, by name and returns the resulted list.  

# Filter:
The filters will only be applied to Queries
- Actor Filters:
Words: A given list of actors will be traversed and any actor that doesn't have the required keywords in their career description will be filtered out.
Awards: A given list of actors will be traversed and any actor that hasn't received the required awards will be filtered out.

- Video Filters:
Year: A given list of videos will be traversed and any video that is not made in one of the required years will be filtered out.
Genre: A given list of videos will be traversed and any video that doesn't have all the required genres will be filtered out.

# Query:
- Actor queries:
There are 3 types of queries for the actors: average, awards and filter description.  
Average Query sorts all actors by their average rating (calculated using actor's filmography ratings) in a specified order. After, the last few elements in the list are removed until list has a size of N (number mentioned in the action's data).  
Awards Query sorts only the actors with given based on their number of overall awards in a specified order.  
Filter Description Query sorts only the actors with given keywords in their career descriptions based on their names in a specified order.  

- Video Queries:
There are 4 types of queries for the videos: rating, favourite and most viewed.
Rating Query sorts all videos by their average rating in a specified order. After, the last few elements in the list are removed until list has a size of N (number mentioned in the action's data).  
Favourite Query sorts all videos by their number of favourites in a specified order. After, the last few elements in the list are removed until list has a size of N (number mentioned in the action's data).  
Most Viewed Query sorts all videos by their number of views in a specified order. After, the last few elements in the list are removed until list has a size of N(number mentioned in the action's data).

- User Queries:
There is only 1 type of query for the users: number of ratings.
Number of Ratings Query sorts all users by their number of ratings in a specified order. After, the last few elements in the list are removed until list has a size of N (number mentioned in the action's data).  

The second sorting criteria for any type of query is the name.  

## Sort Classes:
# Sort Actor:
There are 3 implemented sorting methods for actors: by name, by awards and by average score.
Sorting actors by names is done by simply lexicographically comparing actors' names.  
Sorting actors by number of overall awards is realised by calculating the difference in awards number of each two actors and if the difference is zero, they will be compared based on names.  
Sorting actors by their average rating is achieved by comparing each two actors' average ratings and if they are equal, then the actors will be sorted in alphabetical order. Only actors that have an average rating different from zero are sorted.  

# Sort User:
There is only 1 method for sorting users, by number of ratings.
The sorting method uses the users' number of ratings attribute in order to compare the users and if two users have the same number of ratings, they will be compared based on their names. Only users that gave ratings are sorted.

# Sort Video:
There are 4 methods for sorting videos: by length, by number of views, by average rating and by number of favourites and one method for sorting genres based on popularity.  
Sorting videos by length is done by comparing each two videos' durations and if they have the same value, then the videos will be compared based on their names.  
Sorting videos by views is done by comparing each two videos' number of views and if they have the same value, then the videos will be compared based on their names. Only videos with views are sorted.  
Sorting genres by views is done by mapping and calculating each genre's overall views and then by comparing genres based on their mapped views. Only genres with views are sorted.  
Sorting videos by favourites is done by comparing each two videos' number of favourites and if they have the same value, then if needed, the videos will be compared based on their names (only for queries, but not for recommendations). Only videos that appeared in at least one user's list of favourites can be sorted.  
Sorting videos by average rating is done by comparing each two videos' number of favourites and if they have the same value, then if needed, the videos will be compared based on their names (only for queries, but not for recommendations). In case this function was called by a Recommendation's Search method, then the videos that weren't rated will also be sorted, but in a query's case, only rated videos will be sorted.  

## Util Classes:
# User Util:
Class containing a method capable of searching a user in the Database based on a username.

# Actor Util:
Class containing a method returning all actor names from a provided list of actors.

# Video Util:
Class containing a method for returning the names of all the videos in a specific genre, a method for finding a video, a method returning all videos in a genre, and two methods calculating all views and number of favourites before any action is performed.
