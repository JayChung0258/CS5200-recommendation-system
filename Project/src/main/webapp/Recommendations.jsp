<%@ include file="navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Recommendations</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        h1 { color: #333; text-align: center; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 600px; margin: 20px auto; }
        label { display: block; margin-top: 10px; }
        input[type="number"] { width: 100%; padding: 8px; margin-top: 4px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; margin-top: 20px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #45a049; }
        .error { color: red; font-size: 0.9em; }
        #resultBox { display: none; margin-top: 20px; padding: 15px; background-color: #e0f7fa; border-left: 4px solid #00acc1; }
    </style>
</head>
<body>

<h1>User Product Recommendations</h1>

<form id="predictForm" onsubmit="return validateAndSend();">
    <!-- 9个特征，注意表单输入框的 id 为后面 JS 获取值做准备 -->
    <label>Monthly Income (USD):
        <input type="number" id="Monthly_Income" step="0.01" min="0" max="100000" required>
    </label>
    <label>Daily Entertainment Time (hrs):
        <input type="number" id="Daily_Entertainment" step="0.1" min="0" max="24" required>
    </label>
    <label>Daily Social Media Time (hrs):
        <input type="number" id="Daily_Social_Media" step="0.1" min="0" max="24" required>
    </label>
    <label>Daily Video Content Time (hrs):
        <input type="number" id="Daily_Video" step="0.1" min="0" max="24" required>
    </label>
    <label>Daily Messaging Time (hrs):
        <input type="number" id="Daily_Messaging" step="0.1" min="0" max="24" required>
    </label>
    <label>Daily Gaming Time (hrs):
        <input type="number" id="Daily_Gaming" step="0.1" min="0" max="24" required>
    </label>
    <label>Time Spent in Online Communities (hrs):
        <input type="number" id="Online_Communities" step="0.1" min="0" max="24" required>
    </label>
    <label>Monthly Expenditure on Entertainment (USD):
        <input type="number" id="Monthly_Expenditure" step="0.01" min="0" max="10000" required>
    </label>
    <label>Screen Time (hrs):
        <input type="number" id="Screen_Time" step="0.1" min="0" max="24" required>
    </label>
    <input type="submit" value="Predict">
    <div class="error" id="errorMsg"></div>
</form>

<div id="resultBox">
    <strong>Prediction Results:</strong>
    <p id="prediction1"></p>
    <p id="prediction2"></p>
</div>

<script>
function validateAndSend() {
    // 定义最大值（你可根据需要修改）
    const maxValues = {
        Monthly_Income: 100000,
        Daily_Entertainment: 24,
        Daily_Social_Media: 24,
        Daily_Video: 24,
        Daily_Messaging: 24,
        Daily_Gaming: 24,
        Online_Communities: 24,
        Monthly_Expenditure: 10000,
        Screen_Time: 24
    };
    let valid = true;
    let errorMsg = "";

    // 检查各字段是否满足范围
    Object.keys(maxValues).forEach(function(field) {
        const value = parseFloat(document.getElementById(field).value);
        if (isNaN(value) || value < 0 || value > maxValues[field]) {
            valid = false;
            errorMsg += "Invalid input for " + field.replace("_", " ") + ": " + value + ". Must be between 0 and " + maxValues[field] + "<br>";
        }
    });

    if (!valid) {
        document.getElementById("errorMsg").innerHTML = errorMsg;
        return false;
    }
    // 清除错误信息
    document.getElementById("errorMsg").innerHTML = "";

    // 构造 JSON 数据，注意键名需与 FastAPI 中定义的 alias 完全一致
    const data = {
        "Monthly Income (USD)": parseFloat(document.getElementById("Monthly_Income").value),
        "Daily Entertainment Time (hrs)": parseFloat(document.getElementById("Daily_Entertainment").value),
        "Daily Social Media Time (hrs)": parseFloat(document.getElementById("Daily_Social_Media").value),
        "Daily Video Content Time (hrs)": parseFloat(document.getElementById("Daily_Video").value),
        "Daily Messaging Time (hrs)": parseFloat(document.getElementById("Daily_Messaging").value),
        "Daily Gaming Time (hrs)": parseFloat(document.getElementById("Daily_Gaming").value),
        "Time Spent in Online Communities (hrs)": parseFloat(document.getElementById("Online_Communities").value),
        "Monthly Expenditure on Entertainment (USD)": parseFloat(document.getElementById("Monthly_Expenditure").value),
        "Screen Time (hrs)": parseFloat(document.getElementById("Screen_Time").value)
    };

    // 调用 FastAPI 接口，需要确保 FastAPI 服务正在 http://localhost:8000/predict 上运行
    fetch("http://localhost:8000/predict", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(function(response) {
        if (!response.ok) {
            throw new Error("Network response was not ok: " + response.status);
        }
        return response.json();
    })
    .then(function(result) {
        document.getElementById("resultBox").style.display = "block";
        document.getElementById("prediction1").innerText = "Model1 Prediction: " + result.model1_prediction;
        document.getElementById("prediction2").innerText = "Model2 Prediction: " + result.model2_prediction;
    })
    .catch(function(error) {
        document.getElementById("errorMsg").innerText = "Failed to fetch prediction: " + error;
    });

    return false;  // 防止表单默认提交
}
</script>

</body>
</html>
