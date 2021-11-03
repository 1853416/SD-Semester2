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

var bookingData = {};

const usersCollection = database.collection('TestBookingEmail');

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


const patient = localStorage.getItem('PatientName');
  const pCheck = database.collection('TestBookingEmail').doc('0001');
  pCheck.get()
  .then((docSnapshot) => {
    if (docSnapshot.exists) {
      pCheck.onSnapshot((doc) => {      
       
  bookingData =  doc.data();
  

  document.getElementById("pName").innerHTML = bookingData['PatientName'];
  document.getElementById("dateOfVisit").innerHTML = bookingData['DateOfVisit'];
  document.getElementById("appNotes").innerHTML = bookingData['AppNotes'];
  document.getElementById("script").innerHTML = bookingData['Script'];
  document.getElementById("dName").innerHTML = bookingData['DoctorName'];
  document.getElementById("time").innerHTML = bookingData['Time'];

      });

    } 
    else {

      Toast.show('Not found!! ','success')    }
  });


window.addEventListener('load', () =>{
  const patName = localStorage.getItem('PatientName');
  const usersCollection = database.collection('TestBookingEmail'); 

});
