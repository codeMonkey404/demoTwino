# demoTwino

Basic implementation of task
Not enoth time to make normal testing and validation/ swagger/ security/ properties.
No data provided for validation.

Read comments in the code to get into the logic. 

test data for create loan

{
    "personalId": "cd9a9852-cdc6-4932-a9de-6520d68ad8a2",
    "firstName": "String",
    "lastName": "String",
    "birthDate": "1980-07-24",
    "employer": "String",
    "salary": "200",
    "monthlyLiability": "50",
    "requestedAmount": "400",
    "requestedTermMonths": "12"
}

test data for list loans
{
    "sorting":{
        "field": "firstName",
        "orderDirectionASC": "true"
    },
    "paging":{
        "pageSize": "1",
        "pageNumber": "1"
    }
}

test data for mark loan

{
    "id" : "f86d7b40-2bc2-4942-8c7a-b2a84163a99d",
    "mark" : "Approved"
}

test data for delete loan

{
    "id" : "f6323dbf-23ae-4526-a5b3-03d3f5bb98be"
}

the id in this requests should be changed to actual



