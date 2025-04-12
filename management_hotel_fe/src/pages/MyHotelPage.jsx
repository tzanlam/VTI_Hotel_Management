import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Card, Row, Col, Spin, Typography } from "antd";
import { fetchHotelByAccountId } from "../redux/slice/hotelSlice";
import { useNavigate } from "react-router-dom";

const { Title } = Typography;

const MyHotelPage = () => {
  const { hotel, loading } = useSelector((state) => state.hotel);
  const dispatch = useDispatch();
  const [accountId, setAccountId] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    const storedAccountId = localStorage.getItem("accountId");
    if (storedAccountId) {
        setAccountId(storedAccountId);
    }
}, []);

useEffect(() => {
    if (accountId) {
        dispatch(fetchHotelByAccountId(accountId));

    }
}, [dispatch, accountId]);  


    const handleSelectHotel = (hotelId) => {
      localStorage.setItem("hotelId", hotelId)
      navigate("/home")
    }


  if (loading) return <Spin size="large" />;
  if (!hotel || hotel.length === 0 || hotel === null) return <p>Không có khách sạn nào.</p>;

  return (
    <div style={{ padding: "20px" }}>
      <Title level={2}>Danh sách khách sạn của bạn</Title>
      <Row gutter={[16, 16]}>
        {hotel.map((hotel) => (
          <Col key={hotel.id} span={6}>
            <Card
              hoverable
              cover={<img alt={hotel.name} src={hotel.image} style={{ height: "200px", objectFit: "cover" }} />}
              onClick={()=> handleSelectHotel(hotel.id)}
            >
              <Title level={4}>{hotel.name}</Title>
              <p>Địa chỉ: {hotel.address}</p>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default MyHotelPage;
