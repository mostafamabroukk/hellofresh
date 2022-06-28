# Solution

* REST API: "/api/event" to receive the points.
* REST API: "/api/stats" to display the stats as in the example response.


* Only two methods in the controller which correspond to the two APIs.
* An object called Point which holds the coordinates x, y, and timestamp.
* The service has the method responsible to filter the points (collectAllPoints). The process is done in O(1) because I am looping over all points, and keep only the points which fulfill the condition mentioned (data points that lie within the past 60 seconds). Another two methods in the service to check the condition and output the formatted string.