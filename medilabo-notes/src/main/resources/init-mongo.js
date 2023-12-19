db = db.getSiblingDB('ML_Notes_OC');
db.createUser(
    {
          user: "",
          pwd: "",
          roles: [
                {
                      role: "readWrite",
                      db: "ML_Notes_OC"
                }
          ]
    }
);
db.createCollection('notes');

db.notes.insert([
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae4"
          },
          "date": {
                "$date": "2023-01-01T07:04:03.321Z"
          },
          "patId": "85c4901e-5ee7-43d9-a126-8a45835ad91f",
          "patient": "Test TestNone",
          "note": "La patiente déclare qu'elle se sent très bien. Poids égal ou inférieur au poids recommandé."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae5"
          },
          "date": {
                "$date": "2023-01-01T07:04:03.321Z"
          },
          "patId": "5f71a9a7-8411-4af1-bb5e-b101a9c0fd72",
          "patient": "Test TestBorderline",
          "note": "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae6"
          },
          "date": {
                "$date": "2023-02-01T07:04:03.321Z"
          },
          "patId": "5f71a9a7-8411-4af1-bb5e-b101a9c0fd72",
          "patient": "Test TestBorderline",
          "note": "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormal."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae7"
          },
          "date": {
                "$date": "2023-01-01T07:04:03.321Z"
          },
          "patId": "83fc61fd-7143-44a8-9445-e0329e50a1eb",
          "patient": "Test TestInDanger",
          "note": "Le patient déclare qu'il fume depuis peu."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae8"
          },
          "date": {
                "$date": "2023-02-01T07:04:03.321Z"
          },
          "patId": "83fc61fd-7143-44a8-9445-e0329e50a1eb",
          "patient": "Test TestInDanger",
          "note": "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d'apnée respiratoire anormales. Tests de laboratoire indiquant un taux de cholestérol LDL élevé."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19ae9"
          },
          "date": {
                "$date": "2023-01-01T07:04:03.321Z"
          },
          "patId": "fc75ee91-3e8e-4de9-b4e2-f39556cd1dd2",
          "patient": "Test TestEarlyOnset",
          "note": "Le patient déclare qu'il lui est devenu difficile de monter les escaliers. Il se plaint également d'être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. Réaction aux médicament."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19aea"
          },
          "date": {
                "$date": "2023-02-01T07:04:03.321Z"
          },
          "patId": "fc75ee91-3e8e-4de9-b4e2-f39556cd1dd2",
          "patient": "Test TestEarlyOnset",
          "note": "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19aeb"
          },
          "date": {
                "$date": "2023-03-01T07:04:03.321Z"
          },
          "patId": "fc75ee91-3e8e-4de9-b4e2-f39556cd1dd2",
          "patient": "Test TestEarlyOnset",
          "note": "Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé."
    },
    {
          "_id": {
                "$oid": "653f7b618e8391f46ad19aec"
          },
          "date": {
                "$date": "2023-04-01T07:04:03.321Z"
          },
          "patId": "fc75ee91-3e8e-4de9-b4e2-f39556cd1dd2",
          "patient": "Test TestEarlyOnset",
          "note": "Taille, Poids, Cholestérol, Vertige et Réaction."
    }
]);