var firebaseConfig = {
  apiKey: "AIzaSyABC45epEv116XDWIa-Oag1g9jzjyLDBrY",
  authDomain: "mpt-web-tester.firebaseapp.com",
  projectId: "mpt-web-tester",
  storageBucket: "mpt-web-tester.appspot.com",
  messagingSenderId: "818343226778",
  appId: "1:818343226778:web:67be5a6916ab3b02c71e23"  
};

firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();
const database = firebase.firestore();
var appointData = {};

const usersCollection = database.collection('TestingBookings');

const patient = localStorage.getItem('patientName');
  const pCheck = database.collection('TestingBookings').doc('000006');
  pCheck.get()
  .then((docSnapshot) => {
    if (docSnapshot.exists) {
      pCheck.onSnapshot((doc) => {      
       
      appointData=  doc.data();

      document.getElementById("pname").innerHTML = appointData['patientName'];
      document.getElementById("dateOfVisit").innerHTML = appointData['dateOfVisit'];
      document.getElementById("appNotes").innerHTML = appointData['appNotes'];
      document.getElementById("script").innerHTML = appointData['script'];
      });

    } 
    
    else {
      alert("not found")// create the document
    }
  });

window.addEventListener('load', () =>{
  const patName = localStorage.getItem('patientName');
  const usersCollection = database.collection('TestingBookings'); 
});