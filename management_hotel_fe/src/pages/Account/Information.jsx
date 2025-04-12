import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { fetchAccountById } from "../../redux/slice/accountSlice";
import { Card, Spin, Typography } from "antd";
import "../../assets/css/Information.css";

const { Title } = Typography;

const Information = () => {
  const dispatch = useDispatch();
  const { accountId } = useParams();
  const { account, loading } = useSelector((state) => state.account);
  const [listHotels, setListHotels] = useState([]); // State lưu danh sách tên khách sạn

  useEffect(() => {
    if (accountId) {
      dispatch(fetchAccountById(accountId));
    }
  }, [dispatch, accountId]);

  useEffect(() => {
    if (account?.hotels) {
      // Lấy danh sách tên hotel
      const hotelNames = account.hotels.map((hotel) => hotel.name);
      setListHotels(hotelNames);
    }
  }, [account]);

  if (loading) return <Spin fullscreen tip="Đang tải dữ liệu..." size="large" />;

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: 50 }}>
      <Card
        title={
          <Title level={3} style={{ textAlign: "center", color: "#1890ff", fontSize: "30px", fontFamily: "serif" }}>
            Thông tin tài khoản
          </Title>
        }
        style={{
          width: 900,
          padding: 20,
          borderRadius: 10,
          boxShadow: "0 4px 10px rgba(49, 33, 33, 0.1)",
          background: "#f9f9f9",
        }}
      >
        <div className="info-item">
          <strong>Tên:</strong> {account?.fullName || "Không có dữ liệu"}
        </div>
        <div className="info-item">
          <strong>Email:</strong> {account?.email || "Không có dữ liệu"}
        </div>
        <div className="info-item">
          <strong>Chức vụ:</strong> {account?.position || "Không có dữ liệu"}
        </div>
        <div className="info-item">
          <strong>Số điện thoại:</strong> {account?.phone || "Không có dữ liệu"}
        </div>
        <div className="info-item">
          <strong>Đang làm việc tại: </strong>
          {listHotels.length > 0 ? listHotels.join(", ") : "Không có dữ liệu"}
        </div>
        <div className="info-item">
          <strong>Trạng thái:</strong> {account?.status || "Không có dữ liệu"}
        </div>
      </Card>
    </div>
  );
};

export default Information;
