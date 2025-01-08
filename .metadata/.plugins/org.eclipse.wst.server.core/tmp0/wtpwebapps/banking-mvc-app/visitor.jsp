<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visitor Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-group input:focus,
        .form-group select:focus {
            border-color: #007bff;
            outline: none;
        }

        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            resize: vertical;
        }

        .form-group textarea:focus {
            border-color: #007bff;
            outline: none;
        }

        .form-group .error {
            color: #e74c3c;
            font-size: 0.875em;
        }

        .form-group .success {
            color: #2ecc71;
            font-size: 0.875em;
        }

        .form-group button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Visitor Form</h1>
        <form action="saveVisitor" method="get">
            <div class="form-group">
                <label for="visitorId">Visitor ID</label>
                <input type="text" id="visitorId" name="visitorId" required>
            </div>
            <div class="form-group">
                <label for="visitorName">Visitor Name</label>
                <input type="text" id="visitorName" name="visitorName" required>
            </div>
            <div class="form-group">
                <label for="purpose">Purpose</label>
                <input type="text" id="purpose" name="purpose" rows="4" required>
            </div>
            <div class="form-group">
                <label for="mobileNumber">Mobile Number</label>
                <input type="tel" id="mobileNumber" name="mobileNumber" required pattern="[0-9]{10}" >
            </div>
            <div class="form-group">
                <button type="submit">Submit</button>
                <input type="submit" value="Update" formaction="updateVisitor">
                <input type="submit" value="Delete" formaction="deleteVisitor">
                <input type="submit" value="Find All Visitors" formaction="findAllVisitor">
                <input type="submit" value="Find Visitor" formaction="findVisitor">
                <input type="submit" value="Check Visitor Exist" formaction="checkVisitor">
            </div>
        </form>
    </div>
</body>
</html>