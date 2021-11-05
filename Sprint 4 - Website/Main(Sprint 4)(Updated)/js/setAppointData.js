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
const auth = firebase.auth();
const database = firebase.firestore();
var appointData = {};
var appointData2 = {};
var appointData3 = {};
var pat;
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
document.addEventListener('DOMContentLoaded',()=>Toast.init());

  var pname = document.getElementById('pname');
  var dname = document.getElementById('dname');
  var time = document.getElementById('StartTime');
  var dateOfVisit = document.getElementById('startDate');
  var appNotes = document.getElementById('appNotes');
  var script = document.getElementById('script');
  const btnSave = document.getElementById('btnSave');

  window.addEventListener('load', () =>{
    console.log(localStorage.getItem('FileNew'));
  
    const pCheck = database.collection('Bookings').doc(localStorage.getItem('FileNew'));
    pCheck.get()
    .then((docSnapshot) => {
      if (docSnapshot.exists) {      
        pCheck.onSnapshot((doc) => {        
        appointData=  doc.data();
        pat=appointData['patient_documentID'];
        docnam=appointData['doctor_documentID'];
       
        const pCheck2 = database.collection('Patient').doc(pat);
        pCheck2.get()
        .then((docSnapshot) => {
          if (docSnapshot.exists) {      
            pCheck2.onSnapshot((doc2) => {        
            appointData2=  doc2.data();
            const pCheck3 = database.collection('Doctor').doc(docnam);
            pCheck3.get()
            .then((docSnapshot) => {
              if (docSnapshot.exists) {      
                pCheck3.onSnapshot((doc3) => {        
                appointData3=  doc3.data();
                
                document.getElementById("pname").innerHTML = appointData2['FirstName'];
                document.getElementById('dname').innerHTML = 'Dr. ' +appointData3['LastName'];
                console.log(appointData3['FirstName']);
                document.getElementById("dateOfVisit").innerHTML = appointData['dateOfVisit'];
                document.getElementById("StartTime").innerHTML = appointData['time'];
                
                });
          
              } 
              
              else {
                Toast.show('Not found!! ','error')
                console.log('error');
              }
            });


            
            
            
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

  
const usersCollection = database.collection('Bookings'); 

btnSave.addEventListener('click', e => {
 usersCollection.doc(localStorage.getItem('FileNew')).update({
      
      "AppNotes": appNotes.value,
      "Script": script.value
     
      
    })
    .then(()=>{
      Toast.show('Email sent to client ,data saved succesfully ,you will be redirected shortly!! ','success')
      console.log('Data has been saved successfully !')})
      sendEmail()
    .catch(error => {
      Toast.show('Failed to update !! ','error')
    });

});

function sendEmail() {
  
  console.log(appointData2['Email'])
  Email.send({
  Host: "smtp.gmail.com",
  Username : "notdiscoveryemails2@gmail.com",
  Password : "SDgroup12",
  To :appointData2['Email'],
  From : "notdiscovery1@gmail.com",
  Subject : "Appointment Script",
  Body : "Good Day" +appointData2['FirstName']+ ","+"<br>" +  "<br>" +  "<br>" +  "Your script with "+appointData2['FirstName'] + " at " + appointData['time'] +" on " + appointData['dateOfVisit'] +"." + "<br>" + "<br>" +  "<br>" +script.value ,
  
  }).then(
    //Toast.show('Email sent succesfully !! ','success')
  );
  var delayInMilliseconds = 4500; //1 second
  setTimeout(function() {
    window.location.replace("../html/docHomePage.html");
  }, delayInMilliseconds);

}