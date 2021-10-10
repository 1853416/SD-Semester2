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
var doctorData= {};


window.addEventListener('load', () => {

    // Via local Storage
    const phone = localStorage.getItem('Phone');
    
    
    const phoneCheck = database.collection('Doctor').doc(phone);
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
          doctorData=docSnapshot.data();
          console.log(doctorData['LastName']);
          document.getElementById("DrNamehead").textContent = 'Dr '+ doctorData['LastName'];
        });
      } else {
        alert("not found")// create the document
      }
    });


})
