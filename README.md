# SwipejobsAPI

# Technical test for Swipejobs
Matching engine that processes requests based on available jobs and workers' position

# Getting Started
This project is written and built on IntelliJ. Build tool is Maven and API is written in Java using SpringBoot
Follow the instructions below to run the application
```
1. Run the Application.java file
   Right click on the Application.java file and select "Run 'Application'". This should give you an instance of the       Application in the Run Configuration that can be added as an artefact to the Application Server where you want this application to run.
   
   OR
   
mvn clean install
```
# Integrations
This application has a dependency on the below APIs to read the list of available jobs and workers
```
API endpoint for getting workers info : http://test.swipejobs.com/api/workers
API endpoint for getting jobs info    : http://test.swipejobs.com/api/jobs

```

# API Documentation
### GET /jobmatcher/{workerId}
This REST API will match the relevant and best matching jobs for the worker and return the list of jobs that match for the given worker based on skills, certifications, location preference and Driver Licence requirement

After the Applicaiton is ran, you should be able to run this API with Localhost and in the configured port OR with the endpoint /jobmatcher/{workerId} on any remote Application Server.

Ex: GET http://localhost:8080//jobmatcher/8 should return
```
[
    {
        "driverLicenseRequired": true,
        "requiredCertificates": [
            "Outstanding Memory Award",
            "Calm in the Eye of the Storm",
            "Marvelous Multitasker"
        ],
        "location": {
            "longitude": "14.312687",
            "latitude": "49.828395"
        },
        "billRate": "$7.47",
        "workersRequired": 4,
        "startDate": 1448350525451,
        "about": "Exercitation sint est et fugiat et. Consequat pariatur cupidatat exercitation aliqua dolor pariatur labore aliquip quis irure irure in laboris cillum. Veniam fugiat quis do laborum est exercitation voluptate dolore anim nulla deserunt. Adipisicing dolore culpa commodo aute qui irure ad id exercitation tempor. In in pariatur laborum irure irure. Ut dolor incididunt consectetur quis id fugiat elit sint proident nostrud culpa enim consectetur.",
        "jobTitle": "Chief Cheerleader",
        "company": "Pholio",
        "guid": "562f66aa89c9c662fa538fb7",
        "jobId": "24"
    },
    {
        "driverLicenseRequired": true,
        "requiredCertificates": [
            "Calm in the Eye of the Storm",
            "Healthy Living Promoter",
            "The Behind the Scenes Wonder"
        ],
        "location": {
            "longitude": "14.316602",
            "latitude": "50.022868"
        },
        "billRate": "$17.79",
        "workersRequired": 2,
        "startDate": 1447171645062,
        "about": "Minim commodo amet elit Lorem fugiat non fugiat irure irure. Fugiat aute aliqua ea veniam amet qui tempor elit nisi Lorem commodo aliquip cillum. Id eiusmod ea deserunt adipisicing mollit et. Do quis laboris cupidatat occaecat aute aliqua culpa non ea reprehenderit tempor eu. Duis cillum voluptate pariatur eu eu ullamco laboris. Sit ut cillum ipsum enim aute quis ea eu laborum do ipsum. Eu adipisicing eiusmod eiusmod nostrud aute aliquip magna ad fugiat incididunt.",
        "jobTitle": "Chief Troublemaker",
        "company": "Uxmox",
        "guid": "562f66aa60c4be83d4b592ec",
        "jobId": "25"
    },
    {
        "driverLicenseRequired": true,
        "requiredCertificates": [
            "Office Lunch Expert"
        ],
        "location": {
            "longitude": "14.293204",
            "latitude": "50.266116"
        },
        "billRate": "$6.21",
        "workersRequired": 5,
        "startDate": 1446502360263,
        "about": "Labore tempor aliqua occaecat eiusmod deserunt incididunt qui voluptate laboris fugiat laborum sint eu. In ad ut dolor occaecat amet deserunt pariatur ea fugiat occaecat. Elit est qui quis irure Lorem ullamco Lorem nisi anim cupidatat nostrud qui proident.",
        "jobTitle": "The Resinator",
        "company": "Lovepad",
        "guid": "562f66aa7383f3a5241674c8",
        "jobId": "11"
    }
]
```

# Testing
Run the below Maven command for running the tests
```
mvn test
```


