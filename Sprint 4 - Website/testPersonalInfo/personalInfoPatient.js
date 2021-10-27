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
  
  const usersCollection = database.collection('testPatInfo');
  
  const doctor = localStorage.getItem('firstName');
  const docInfo = database.collection('testPatInfo').doc('00001');
    pCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        docInfo.onSnapshot((doc) => {      
         
        appointData =  doc.data();
  
        document.getElementById("fname").innerHTML = appointData['firstName'];
        document.getElementById("lname").innerHTML = appointData['lastName'];
        document.getElementById("id").innerHTML = appointData['ID'];
        document.getElementById("email").innerHTML = appointData['email'];
        
        });
  
      } 
      
      else {
        alert("not found")// create the document
      }
    });
  
  window.addEventListener('load', () =>{
    const patName = localStorage.getItem('firstName');
    const usersCollection = database.collection('testPatInfo'); 
  });