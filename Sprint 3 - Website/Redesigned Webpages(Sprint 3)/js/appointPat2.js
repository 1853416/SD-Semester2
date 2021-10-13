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
var brk = document.createElement("br");

const patient = localStorage.getItem('userName');
console.log(patient);
  const pCheck = database.collection('TestingBookings').where("patientName", "==" ,patient);
  pCheck.get()
  .then((docSnapshot) => {
	docSnapshot.forEach((doc) =>{
		appointData=  doc.data();
		var cards = document.createElement("div");
		cards.setAttribute("class","card2");

		cards.appendChild(brk);
		var nom = document.createElement("Label");
		nom.innerHTML = "Doctor's Name: ";
		cards.appendChild(nom);
	
		var docId = document.createElement("p");
		docId.innerHTML = appointData['doctorID'];
		cards.appendChild(docId);

		var lbldate = document.createElement("Label");
		lbldate.innerHTML = "Appointment Date: ";
		cards.appendChild(lbldate);
	
		var date = document.createElement("p");
		date.innerHTML = appointData['dateOfVisit'];
		cards.appendChild(date);
		
		var link = document.createElement("p");
		link.innerHTML = " <a href='appointmentFormDisplay.html'>View Appointment Form</a>";
		cards.appendChild(link);

		document.body.appendChild(cards);
		document.body.insertBefore(brk, cards);	
		document.body.appendChild(brk);	
      		document.getElementById("p1").innerHTML = appointData['doctorID'];
      		document.getElementById("d1").innerHTML = appointData['dateOfVisit'];
    
	});
  });

window.addEventListener('load', () =>{
  const patName = localStorage.getItem('patientName');
  const usersCollection = database.collection('TestingBookings'); 
});