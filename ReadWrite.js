//Below required code for html ,place it in header or below body BUT NOT in body !!! Remember to include your script src tags for js files
////////////
//<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-app.js"></script>
//<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-auth.js"></script>
//<script src="https://www.gstatic.com/firebasejs/8.6.8/firebase-firestore.js"></script>

//Next code is to set and initialize the database for us to use .Ensure it is at the TOP of your js Files 

var firebaseConfig = {//this is adress reference to the database you dont have to change anything here 
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

  const usersCollection = database.collection('Doctor');//the 'Doctor' variable can be changed depending on what Collection you would like to work with in the database 

  ////////////
  //Writing to the database 

  const ID = usersCollection.doc(phone.value).set({//phone.value is the ID of the document we would like to write to the database keep this in mind hen trying to reference things 
    Email: email.value,//these are our fields ,The variable Email,Fields,FirstName etc are variables we want to add to the documents .How you name it here is how it gets set in the database. Values like email.values is the data we want to assign to our variables 
    Fields: field.value,
    FirstName: first_name.value,
    ID: idNum.value,
    LastName:last_name.value,
    Password: password.value,
    Phone:phone.value,
    Qualifications: qualifications.value,
    Years:years.value


  })
  .then(()=>{
    alert('User Created!!')//just an alert message that can be used to show us that communication was succesful to the database 
  window.location.replace("../html/docLogin.html");
    console.log('Data has been saved successfully !')})//can be used instead of alert check the consol when running html in browser for this one 
  .catch(error => {
    console.error(error)
  });

  //More write examples 

  const ID = usersCollection.doc('0000000000').collection('Scripts').add({
    "Patient Name": pname.value,
    "Date of visit": dateOfVisit.value,
    "App Notes": appNotes.value,
    "Perscription": script.value,
    
  })
  .then(()=>{
    alert('Info saved!!')
    console.log('Data has been saved successfully !')})
  .catch(error => {
    console.error(error)
  });







  ///////
  //Reading from the database!!! 
  var doctorData= {};//very important .Bitmap used below to retrieve all fields from database.I have not found any more effecinet way to retrieve info for a specific user but hey it works so why change it  

  const phoneCheck = database.collection('Doctor').doc(phone.value);;//the 'Doctor' variiable can be changed depending on what Collection you would like to work with in the database //phone.value can also be changed depending on what Document you wnat to find 
        phoneCheck.get()
        .then((docSnapshot) => {//following code sets all the fields we want to retrieve to the doctorData bitmap we declared above 
          if (docSnapshot.exists) {
            phoneCheck.onSnapshot((doc) => {       
              doctorData=docSnapshot.data();//set the fields from database to doctorData bitmap
              
            });
          } else {
            alert("not found")// create the document
          }
        });

        //using the data we just read from the database
        doctorData['Password']//Thats it! depending on what field you want to retrieve data from, just  change 'Password' to what ever field you want 

        ///more read examples 

        const phone = localStorage.getItem('Phone');
    const phoneCheck = database.collection('Doctor').doc(phone);
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
          doctorData=docSnapshot.data();
          
          
        });
      } else {
        alert("not found")// create the document
      }
    });
    document.getElementById("DrNamehead").textContent = 'Dr'+ doctorData['LastName'].value;


    //If you  are strugling further please reference the firebase doc below its really helpful.Make sure you reference web version 8 
    //https://firebase.google.com/docs/firestore/manage-data/add-data
