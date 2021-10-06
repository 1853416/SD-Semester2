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
var appointData = {};

const usersCollection = database.collection('TestingBookings');

function tst(){

    var i;
    var y;

    usersCollection.doc('000005').get().then((snapshot) => {
      snapshot.docs.forEach(doc => {console.log(doc.data()); appointData[i] = doc.data(); i++
      ;})
    })

    document.getElementById("pName").innerHTML = JSON.stringify(appointData[1]['patientName']);

}

/*const patient = localStorage.getItem('patientName');
    const pCheck = database.collection('TestingBookings').doc('000005');
    pCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {
        pCheck.onSnapshot((doc) => {       
          appointData=docSnapshot.data();
          
          
        });
      } else {
        alert("not found")// create the document
      }
    });
    document.getElementById("pName").textContent = appointData['patientName'].value;
    document.getElementById("dateOfVisit").textContent = appointData['dateOfVisit'].value;
    document.getElementById("appNotes").textContent = appointData['appNotes'].value;
    document.getElementById("script").textContent = appointData['script'].value;*/



/*window.addEventListener('load', () =>{

    const patName = localStorage.getItem('patientName');
    const patName = localStorage.getItem('patientName');

    const usersCollection = database.collection('TestingBookings'); 

btnSave.addEventListener('click', e => {
    const ID = usersCollection.doc('000003').add({
        "patientName": pname.value,
        "Date of visit": dateOfVisit.value,
        "App Notes": appNotes.value,
        "Perscription": script.value,
        
      })
      .then(()=>{
        alert('Info saved!!')
        console.log('Data has been saved successfully !')})
      .catch(error => {
        console.error(error)
      });

  });
});*/

    
