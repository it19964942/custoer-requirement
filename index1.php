<?php

include "connection.php";


?>
<?php
$con = mysqli_connect("localhost","root","", "chat1");

if (isset($_POST['upload']))
{
	$file = $_FILES['image']['name'];
	
	$query = "INSERT INTO upload(image) VALUES('$file')";
	
	$res = mysqli_query($con,$query);
	
	if($res){
		
		move_uploaded_file($_FILES['image']['tmp_name'],"$file");
	}
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>customer service</title>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="swal("Good job!", "You clicked the button!", "success");"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  
  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="back">

<div class="container">

<div class="col-lg-9">

	<div class="head">
  <p>Our customer stories  better service starts here !</p>
  </div>
  <br>
   <br>
    <br>
  <form action="" name="form1" method="post">
    <div class="form-group">
	
	<div class="what">
	What Products do you neeed ?
	</div>
	
	<br>
      <input type="text" class="form-control" id="item" placeholder="Input your product  names" name="item">
	  <br>
	  
	  
	  <div class="IF"><p>IF your Products is not available in  our website  you can request  that Products in here?</p> </div>
	  <br>
	  
	  
	
    </div>

		 <button name="insert" type="submit" id="submi" class="btn btn-primary">Insert Product</button>
		 <br><br><br>
		 
		
   
  </form>
  <script>
			$("#submi").click(function(){
				
			swal({
		title: "Good job!",
		text: "You clicked the button!",
		icon: "success",
		button: "Aww yiss!",
			});
				
				
				
				
			});
			
			
  
  
		
  
  </script>
  <div class="container">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
						
						
				</div>
				<div class="col-md-6">
						
				</div>
			</div>
		</div>
	</div>
		<a href="index.php"><button name="chat" type="onclick" class="btn btn-primary" >Chat With US</button></a>
		 
		 <br><br><br><br>
		 
		 <h3 class="text">Upload Image</h3>
						<form calss="my-5" method="post"  enctype="multipart/form-data">
							<input type="file" name="image" class="form-control">
							
							<input type="submit" name="upload" id="img" value="UPLOAD" class="btn btn-success my-3">
							<br><br>
							
							
						</form>
  
  
</div>
</div>
<br><br><br>


<div class="col-lg-12">

<table class="table table-bordered">
    <thead>
      
	     <th>No</th>
        <th>Product</th>
		
		<th>Edit</th>
		  <th>Delete</th>
        
      </tr>
    </thead>
	<tbody>
	
	<?php
		$res=mysqli_query($link,"select * from table1");
		while($row=mysqli_fetch_array($res)) 
		{
			echo "<tr>";
			echo "<td>"; echo $row["ID"]; echo"</td>";
			echo "<td>"; echo $row["Item"]; echo"</td>";
			
			
			
			echo "<td>"; ?> <a href="edit.php?id=<?php echo $row["ID"]; ?>"> <button type="button" class="btn btn-success"> Edit Item  </button> <?php echo "</td>";
			echo "<td>"; ?> <a href="delete.php?id=<?php echo $row["ID"]; ?>"> <button type="button" class="btn btn-danger"> Delete Item </button> <?php echo "</td>";
			
			
			echo "</tr>";
		}
	
	?>
	</tbody>
    
  </table>

</div>
</div>
</body>

<?php
	if(isset($_POST["insert"]))
	{
		mysqli_query($link, "insert into table1  values(NULL, '$_POST[item]' )");
		
		?>
		<script type ="text/javascript">
		window.location.href=window.location.href;
		</script>
		<?php
	}
	
	if(isset($_POST["delete"]))
	{
		mysqli_query($link, "delete from  table1  where item='$_POST[item]'") or die(mysqli_error($link));
		?>
		<script type ="text/javascript">
		window.location.href=window.location.href;
		</script>
		<?php
	}
	if(isset($_POST["update"]))
	{
		mysqli_query($link, "update table1 set item='$_POST[item]'  where item='$_POST[item]'") or die(mysqli_error($link));
		?>
		<script type ="text/javascript">
		window.location.href=window.location.href;
		</script>
		<?php
	}






?>
</html>