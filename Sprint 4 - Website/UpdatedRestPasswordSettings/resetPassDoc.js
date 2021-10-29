var firebaseConfig = {
    apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
    authDomain: "mpt-web-tester.firebaseapp.com",
    projectId: "mpt-web-tester",
    storageBucket: "mpt-web-tester.appspot.com",
    messagingSenderId: "818343226778",
    appId: "1:818343226778:web:67be5a6916ab3b02c71e23"
    
  };
    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);
    // Initialize variables
    const auth = firebase.auth();
    const database = firebase.firestore();
  
  
  const usersCollection = database.collection('testPatInfo');//the 'Doctor' variable can be changed depending on what Collection you would like to work with in the database 
  
    ////////////
    //Writing to the database 
  
    btnReset.addEventListener('click', e => {
      const ID = usersCollection.doc('00002').update({
        "password": passOne.value,
        //"dateOfVisit": dateOfVisit.value,
        //"appNotes": appNotes.value,
        //"script": script.value,
          
        })
        .then(()=>{
          alert('Info saved!!')
          console.log('Data has been saved successfully !')})
        .catch(error => {
          console.error(error)
        });
    });