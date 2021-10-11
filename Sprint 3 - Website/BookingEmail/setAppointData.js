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
    var pname = document.getElementById('pname');
    var dname = document.getElementById('dname');
    var time = document.getElementById('time');
    var dateOfVisit = document.getElementById('dateOfVisit');
    var appNotes = document.getElementById('appNotes');
    var script = document.getElementById('script');
    const btnSave = document.getElementById('btnSave');

    
const usersCollection = database.collection('TestBookingEmail'); 

btnSave.addEventListener('click', e => {
    const ID = usersCollection.doc('000').collection('Bookings').add({
        "PatientName": pname.value,
        "DoctorName": dname.value,
        "DateOfVisit": dateOfVisit.value,
        "AppNotes": appNotes.value,
        "Script": script.value,
        "Time": time.value,
        
      })
      .then(()=>{
        alert('Info saved!!')
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });

  });

  function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "notdiscoveryemails@gmail.com",
	Password : "SDgroup12",
	To : 'mmanjra005@gmail.com',
	From : "notdiscoveryemails@gmail.com",
	Subject : "Appointment Confirmation",
	Body : "Your booking is confirmed", 
	}).then(
		message => alert("mail sent successfully")
	);
}