# memsrc-alex-test

This test project uses Grails 3.2.3

## Launching application
1. Clone the project.
2. CD into the project root
3. Launch grails run-app
4. Open url http://localhost:8080 in your browser.


The app will start at the Projects page. In top header bar there is a Setup link leading you to the Setup page. Also, there's a convenience 'H2 DB Console' link to open a UI H2 console close to the current tab.
Consider using only development environment. I did not use other environments while working on the app. H2 database is written into file system at USER_HOME/.memsrc-alex-test-h2. The app also writes logs which get written into USER_HOME/memsrc-alex-test.log.

The web application is stateless: it does not store its state when switching between pages. All data fetching and posting is done using jQuery's ajax functions. For UI styling Bootstrap and original Grails styles are used.'