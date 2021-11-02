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
const Toast={
  init(){
    
    this.el=document.createElement('div');
    this.el.className='toast';
    document.body.appendChild(this.el);
  },
  show(message,state){
    this.hideTimeout=null;

    //clearTimeout(this,hideTimeout);

    this.el.textContent=message;
    this.el.className='toast toast--visible';
    if(state){
      this.el.classList.add(`toast--${state}`);
    }
    this.hideTimeout=setTimeout(()=>{
      this.el.classList.remove('toast--visible');

    },4000);

  }
};



async function pullBook(){
  var phone = localStorage.getItem('Phone');
	
	console.log(phone);
	const db = await database.collection('Bookings').where("patient_documentID", "==", localStorage.getItem('Phone'));
	console.log(db['patient_documentID']);
	db.get().then((querySnapshot) => {
		
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		//console.log("Dr" ,doc.data());
			var x = doc.data();
			
			var cards = document.createElement("div");
			const currentDiv = document.getElementById("div1");
			var brk = document.createElement("br");
			cards.setAttribute("class","card2")
					const db =  database.collection('Doctor').doc(x['doctor_documentID']);
					db.get().then((docSnapshot) => {
						if (docSnapshot.exists)
						{
							
							db.onSnapshot((doc2) => {
								if (doc.data()['Status'] == "confirmed")
								{
                  var drnam = document.createElement("p");
                  drnam.innerHTML = ("Doctor's Name:");
                  cards.appendChild(drnam);
									var nom = document.createElement("h3");
									nom.innerHTML = "Dr " + doc2.data()['LastName'];
									cards.appendChild(nom);
									var spec = document.createElement("p");
									spec.innerHTML = doc2.data()['Fields'];
									cards.appendChild(spec);
                  var drtime = document.createElement("p");
                  drtime.innerHTML = ("Appointment Date & Time");
                  cards.appendChild(drtime);
									var dat = document.createElement("p");
									dat.setAttribute("class", "p")
									dat.innerHTML = x['dateOfVisit'];
									cards.appendChild(dat);                 
									var tim = document.createElement("p");
									tim.setAttribute("class", "p2")
									tim.innerHTML = x['time'];
									cards.appendChild(tim);
									var lin = document.createElement("a");
									var viewapp = document.createElement("BUTTON");
                viewapp.innerHTML = "View Appointment Form";
                viewapp.setAttribute("class", "button");
                viewapp.id= doc.id;
                viewapp.setAttribute("onclick", "Form(this);");
                cards.appendChild(viewapp);
									//console.log(doc.id);
									//console.log("heya");
								}
								else
								{
                  var drnam = document.createElement("p");
                  drnam.innerHTML = ("Doctor's Name:");
                  cards.appendChild(drnam);
									var nom = document.createElement("p");
									nom.innerHTML = "Dr " + doc2.data()['LastName'];
									nom.style.textDecoration = 'line-through';
									cards.appendChild(nom);
									var spec = document.createElement("p");
									spec.innerHTML = doc2.data()['Fields'];
									spec.style.textDecoration = 'line-through';
									cards.appendChild(spec);
                  var drtime = document.createElement("p");
                  drtime.innerHTML = ("Appointment Date & Time");
                  cards.appendChild(drtime);
									var dat = document.createElement("p");
									dat.setAttribute("class", "p")
									dat.innerHTML = x['dateOfVisit'];
									dat.style.textDecoration = 'line-through';
									cards.appendChild(dat);
                  var tim = document.createElement("p");
									tim.setAttribute("class", "p2")
									tim.innerHTML = x['time'];
                  tim.style.textDecoration = 'line-through';
									cards.appendChild(tim);
									cards.appendChild(brk);
									var mail = document.createElement("p");
									mail.innerHTML = "Cancelled";
									cards.appendChild(mail);

                  
								}

								});
							
						}
						else{Toast.show('Not found!! ','error')}
					});
			document.body.insertBefore(brk, currentDiv);	
			document.body.insertBefore(cards, currentDiv);
			
			
    		});
	});
};

function Form($this)
{
  var formid =$this.id;
  localStorage.setItem("FileNew", formid);
  
  window.location.replace("../html/appointmentFormDisplay.html");
}