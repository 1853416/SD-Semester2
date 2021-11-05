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
var appointData2 = {};
var pat;
const back = document.getElementById('back');
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
back.addEventListener('click', e => {
  window.location.replace("../html/appointmentHistoryDoc.html");


});

window.addEventListener('load', () =>{
  
  const pCheck = database.collection('Bookings').doc(localStorage.getItem('FileNew'));
  pCheck.get()
  .then((docSnapshot) => {
    if (docSnapshot.exists) {      
      pCheck.onSnapshot((doc) => {        
      appointData=  doc.data();
      pat=appointData['patient_documentID'];
     
      const pCheck2 = database.collection('Patient').doc(pat);
      pCheck2.get()
      .then((docSnapshot) => {
        if (docSnapshot.exists) {      
          pCheck2.onSnapshot((doc) => {        
          appointData2=  doc.data();
          
          document.getElementById("pname").innerHTML = appointData2['FirstName'];
          document.getElementById("dateOfVisit").innerHTML = appointData['dateOfVisit'];
          document.getElementById("appNotes").innerHTML = appointData['AppNotes'];
          document.getElementById("script").innerHTML = appointData['Script'];
          });
    
        } 
        
        else {
          Toast.show('Not found!! ','error')
        }
      });
      });

    } 
    
    else {
      Toast.show('Not found!! ','error')
    }
  });

});