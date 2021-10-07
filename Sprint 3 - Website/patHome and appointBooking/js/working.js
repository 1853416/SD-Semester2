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
			nws.setAttribute('value', x['FirstName']);
			nws.innerHTML = x['FirstName'];
			grp.appendChild(nws);
    		});
	});

	 //await console.log(x);
	//document.getElementById("demo").innerHTML = doctorData['First Name'];
}

async function pullTim(){	

	const time = await database.collection('Doctor').where("FirstName", "==", localStorage.getItem('docName'));
	time.get().then((querySnapshot) => {
    		querySnapshot.forEach((doc) => {
        		// doc.data() is never undefined for query doc snapshots
        		console.log("Dr" ,doc.data());
			var x = doc.data();
			document.getElementById("w").innerHTML = "q";

			var grp = document.getElementById("times");
			var nws = document.createElement('option');
			nws.innerHTML = x['FirstName'];
			grp.appendChild(nws);
			
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
		
		nam = document.getElementById("drNames");
		nam.options.length = 0;

		var nws = document.createElement('option');
		nws.setAttribute('value', "Blank");
		nws.innerHTML = "Select";
		nam.appendChild(nws);
		
		fld = document.getElementById("field");
		temp = fld.options[fld.selectedIndex].value;
		localStorage.setItem('special', temp);
		//document.getElementById("w").innerHTML = temp;
}

function docSel()
{
		
		nam = document.getElementById("times");
		nam.options.length = 0;

		fld = document.getElementById("field");
		temp = fld.options[fld.selectedIndex].value;
		localStorage.setItem('special', temp);
		//document.getElementById("w").innerHTML = temp;
}


