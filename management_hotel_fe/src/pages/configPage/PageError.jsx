import React from "react";
import { Result, Button } from "antd";
import { useNavigate } from "react-router-dom";

const PageError = () => {
  const navigate = useNavigate();

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <Result
        status="500"
        title="Có lỗi xảy ra!"
        subTitle="Xin lỗi, đã có lỗi xảy ra. Vui lòng thử lại sau."
        extra={
          <Button type="primary" onClick={() => navigate("/")}>
            Quay lại trang chủ
          </Button>
        }
      />
    </div>
  );
};

export default PageError;
