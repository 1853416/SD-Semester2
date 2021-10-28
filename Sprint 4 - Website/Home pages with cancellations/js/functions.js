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

async function pullDoc(){	

	const db = await database.collection('Doctor').where("Fields", "==", localStorage.getItem('special'));
	db.get().then((querySnapshot) => {
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		//console.log("Dr" ,doc.data());
			var x = doc.data();
			

			var grp = document.getElementById("drNames");
			var nws = document.createElement('option');
			nws.setAttribute('value', x['Email']);
			nws.innerHTML = x['FirstName'];
			grp.appendChild(nws);
    		});
	});

	 //await console.log(x);
	//document.getElementById("demo").innerHTML = doctorData['First Name'];
}

async function pullTim(){	

	const time = await database.collection('Doctor').where("Email", "==", localStorage.getItem('docName'));
	time.get().then((querySnapshot) => {
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		//console.log("Dr" ,doc.data());
			var x = doc.data();
			//document.getElementById("w").innerHTML = "q";

			if (x['Hour'] == "Hour")
			{
				s = x['End Time'].split(':')[0];
				t = x['Start Time'].split(":")[0];
				len = s-t;
				for (i=0; i<=len; i++)
				{
					var grp = document.getElementById("times");
					var nws = document.createElement('option');
					nws.innerHTML = parseInt(t)+i + ":00";
					grp.appendChild(nws);
				}
			}

			
			
    		});
	});

}


function myFunction() {
    document.getElementById("myForm").reset();
}

var obj; //These variables facilitate ChangeTxt() and btnChk()
var temp;



/*
*
*/
function fldSel()
{
		
		var nam = document.getElementById("drNames");
		nam.options.length = 0;

		var nws = document.createElement('option');
		nws.setAttribute('value', "Blank");
		nws.innerHTML = "Select";
		nam.appendChild(nws);
		
		var fld = document.getElementById("field");
		temp = fld.options[fld.selectedIndex].value;
		localStorage.setItem('special', temp);
		//document.getElementById("w").innerHTML = temp;
}

function docSel()
{

		var nam = document.getElementById("times");
		nam.options.length = 0;
		
		var fld = document.getElementById("drNames");
		temp = fld.options[fld.selectedIndex].value;
		localStorage.setItem('docName', temp);
		//document.getElementById("w").innerHTML = temp;
}


function book()
{
	var date = document.getElementById("datesAvailable").value;
	//var x = date.options[date.selectedIndex].value;
	var doctor = document.getElementById("drNames");
	var y = doctor.options[doctor.selectedIndex].innerHTML;
	var slot = document.getElementById("times");
	var z = slot.options[slot.selectedIndex].value;

	document.getElementById("w").innerHTML = date;

	const usersCollection = database.collection('TestingBookings');
	const ID = usersCollection.doc('000010').set({
    	dateOfVisit: date,
    	doctorID: 0000000000,
    	patientName: localStorage.getItem('userName'),
    	time: z


  }).then(()=>{
    Toast.show('User Created!! ','success');
  var delayInMilliseconds = 4500; //1 second
	})

}
window.addEventListener('load', () => {

    // Via local Storage
    const phone = localStorage.getItem('Phone');
    console.log(phone);
	const phoneCheck = database.collection('Patient').doc(phone);
    phoneCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        phoneCheck.onSnapshot((doc) => {       
			Patientdata=docSnapshot.data();
          console.log(Patientdata['LastName']);
          document.getElementById("PatNameHead").textContent = Patientdata['FirstName']+' '+ Patientdata['LastName'];
        });
      } else {
        alert("not found")// create the document
      }
    });


})


