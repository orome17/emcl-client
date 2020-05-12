# EMCL CLIENT

## Start server

Start the Server:
src/main/java/com/safeway/emclclient/server/Server.java

This will open a server socket on localhost:7001, wait for connections and print the incoming messages.

## Start the emcl-client

Start emcl-client springboot app:
src/main/java/com/safeway/emclclient/Application.java

## Send a POST request

```
curl --location --request POST 'http://localhost:8080/emcl/customer-update' \
--header 'Content-Type: application/json' \
--data-raw '{
  "eventMetadata": {
    "aggregateId": "1ab03ab0-6f9e-11ea-bc55-0242ac130003",
    "eventType": "HouseholdAssociateEvent",
    "requestId": "c9415ef4-7054-11ea-bc55-0242ac130003",
    "eventTs": 1585333341,
    "version": "1",
    "source": {
      "name": "CCMS",
      "bannerId": "1",
      "clientId": "IASS"
    }
  },
  "data": {
    "profiles": [
      {
          "indicators": [
          {
            "type": "PublishProfile",
            "value": "1"
          }
        ],
        "stores": [
          {
            "terminalNumber": 1,
            "storeNumber": "970",
            "banner": "SAFEWAY"
          }
        ],
        "householdAccounts": [
          {
            "houseHoldId": "990988123041",
            "digitalRegInd": "1"
          }
        ],
        "loyaltyPrograms": [
          {
            "name": "CLUBCARD",
            "number": "43680112576"
          }
        ],
        "name": {
          "firstName": "Colers",
          "lastName": "Mateoser"
        },
        "phones": [
          {
            "purpose": "PRIMARY",
            "number": "5105101237"
          }
        ],
        "email": {
          "text": "colemateo2@example.com"
        }
      }
    ]
  }
}'\'''
```
