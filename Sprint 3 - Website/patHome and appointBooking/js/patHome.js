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
    const pname = "test";
    const dateOfVisit = "test";
    const appNotes = "test";
    const script = "test";
    // const dprel = "test";
    const btnSave = document.getElementById('btnSave');

    

    
    const usersCollection = database.collection('Doctor'); 

async function pullBook(){	

	const db = await database.collection('TestingBookings').where("patientName", "==", "patient2");
	db.get().then((querySnapshot) => {
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		//console.log("Dr" ,doc.data());
			var x = doc.data();
			
			var cards = document.createElement("div");
			const currentDiv = document.getElementById("div1");
			var brk = document.createElement("br");
			cards.setAttribute("class","card")
					const db =  database.collection('Doctor').doc(x['doctorID']);
					db.get().then((docSnapshot) => {
						if (docSnapshot.exists)
						{
							
							db.onSnapshot((doc2) => {

								var nom = document.createElement("h3");
								nom.innerHTML = "Dr " + doc2.data()['Last Name'];
								cards.appendChild(nom);
								var spec = document.createElement("p");
								spec.innerHTML = doc2.data()['Fields'];
								cards.appendChild(spec);
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
								var mail = document.createElement("p");
								mail.setAttribute("class", "p4")
								mail.innerHTML = doc2.data['Email'];
								cards.appendChild(mail);
								console.log("heya");

								});
							
						}
						else{alert("not Found");}
					});
			document.body.insertBefore(brk, currentDiv);	
			document.body.insertBefore(cards, currentDiv);	
			
    		});
	});

}







