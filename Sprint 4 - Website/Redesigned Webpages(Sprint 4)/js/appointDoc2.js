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
  var appointMissedData = {};
  
  const usersCollection = database.collection('TestingBookings');
  
  // initialise variable
  const patient = localStorage.getItem('patientName');
  
  var patientName = localStorage.getItem('patientName');
  var docId = localStorage.getItem('doctorID');
  var time = localStorage.getItem('time');
  var dateOfVisit = localStorage.getItem('dateOfVisit');
  
  
    const pCheck = database.collection('TestingBookings').doc('000006');
    pCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        pCheck.onSnapshot((doc) => {      
         
        appointData=  doc.data();
  
        document.getElementById("p1").innerHTML = appointData['patientName'];
        document.getElementById("d1").innerHTML = appointData['dateOfVisit'];
        });
  
      } 
      
      else {
        alert("not found")// create the document
      }
    });
  
    // Read data for Email
  
    const dataCheck = database.collection('TestingBookings').doc('000006');
    dataCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        dataCheck.onSnapshot((doc) => {      
         
        appointMissedData=  doc.data();
  
        patientName = appointMissedData['patientName'];
        dateOfVisit = appointMissedData['dateOfVisit'];
        docId = appointMissedData['doctorID'];
        time = appointMissedData['time'];
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
  
  
  function sendEmail() {
      Email.send({
      Host: "smtp.gmail.com",
      Username : "notdiscoveryemails2@gmail.com",
      Password : "SDgroup12",
      To : 'mmanjra005@gmail.com',
      From : "notdiscoveryemails2@gmail.com",
      Subject : "Missed Appointment",
      Body : "Good Day " + patientName+ "," + "<br>" +  "<br>" +  "<br>" +  "You have missed your appointment with "+ docId + " on " + dateOfVisit +"." + "<br>" + "<br>" +  "<br>" + "Please reshedule your appointment as soon as possible."  + "<br>" + "<br>"+ "Please bring all of your medication."  + "<br>" +"<br>" + "Thank You for using NotDiscovery!", 
      }).then(
          message => alert("mail sent successfully")
      );
  }