Configuration attributes: roles given to a user.

secured object: method or channel or url

decide method: gives decision success or fail

supports(ConfigAttributes): determines if authManager can parse these configuration attributes.

runasmanager: request authorized, want to change authentication object at runtime, access decision manager can be used to call runAsManager. If authentication object has to be changed at later time, afterInvocationManager can be used.


BasicAuthentication: sending username and password with each request . Should use channel to protect it.  