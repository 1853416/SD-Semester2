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


async function pullBook(){	

	//localStorage.setItem('docName', "0000000000");
	var name = localStorage.getItem('docName');
	
	const db = await database.collection('TestingBookings').where("doctorID", "==", name);
	db.get().then((querySnapshot) => {
		
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		//console.log("Dr" ,doc.data());
			var x = doc.data();
			
			var cards = document.createElement("div");
			const currentDiv = document.getElementById("div1");
			var brk = document.createElement("br");
			cards.setAttribute("class","card")
					const db =  database.collection('Patient').doc(x['patientID']);
					db.get().then((docSnapshot) => {
						if (docSnapshot.exists)
						{
							
							db.onSnapshot((doc2) => {
								if (doc.data()['Status'] == "confirmed")
								{
									var nom = document.createElement("h3");
									nom.innerHTML = doc2.data()['First Name'] +" "+ doc2.data()['Last Name'];
									cards.appendChild(nom);
									
									var dat = document.createElement("p");
									dat.setAttribute("class", "p1")
									dat.innerHTML = x['dateOfVisit'];
									cards.appendChild(dat);
									var tim = document.createElement("p");
									tim.setAttribute("class", "p2")
									tim.innerHTML = x['time'];
									cards.appendChild(tim);
									var hrk = document.createElement("hr");
									cards.appendChild(brk);
									cards.appendChild(hrk);
									var cont = document.createElement("h4");
									cont.innerHTML = ("Contact Details");
									cards.appendChild(cont);
									var cellNum = document.createElement("p");
									cellNum.setAttribute("class", "p4");
									cellNum.innerHTML = doc2.id;
									cards.appendChild(cellNum);
									var mail = document.createElement("p");
									mail.setAttribute("class", "p4");
									mail.innerHTML = doc2.data()['Email'];
									cards.appendChild(mail);
									var lin = document.createElement("a");
									lin.innerHTML = "Cancel";
									lin.href = "#";
									lin.id= doc.id;
									lin.setAttribute("onclick", "cancel(this);");
									cards.appendChild(lin);
									//console.log(doc.id);
									//console.log("heya");
								}
								else
								{
									var nom = document.createElement("h3");
									nom.innerHTML = "Dr " + doc2.data()['Last Name'];
									nom.style.textDecoration = 'line-through';
									cards.appendChild(nom);
									var spec = document.createElement("p");
									spec.innerHTML = doc2.data()['Fields'];
									spec.style.textDecoration = 'line-through';
									cards.appendChild(spec);
									var dat = document.createElement("p");
									dat.setAttribute("class", "p1")
									dat.innerHTML = x['dateOfVisit'];
									dat.style.textDecoration = 'line-through';
									cards.appendChild(dat);
									cards.appendChild(brk);
									var mail = document.createElement("p");
									mail.innerHTML = "Cancelled";
									cards.appendChild(mail);
								}

								});
							
						}
						else{alert("not Found");}
					});
			document.body.insertBefore(brk, currentDiv);	
			document.body.insertBefore(cards, currentDiv);
			
			
    		});
	});

}

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

function cancel($this)
{
	var slt = $this.id;
	const usersCollection = database.collection('TestingBookings');
	const ID = usersCollection.doc(slt).update({
    	Status: "cancelled"


  }).then(()=>{
    location.reload();
	sendEmail();
	})
}

 function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "notdiscoveryemails2@gmail.com",
	Password : "SDgroup12",
	To : 'deshandhevan@gmail.com',
	From : "notdiscoveryemails2@gmail.com",
	Subject : "Appointment Cancellation",
	Body : "Your Appointment has been cancelled", 
	}).then(
		message => alert("mail sent successfully")
	);
}
