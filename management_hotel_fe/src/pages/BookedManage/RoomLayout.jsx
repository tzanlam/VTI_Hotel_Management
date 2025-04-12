import React, { useEffect, useState } from "react";
import { Card, Row, Col, Popover, Button, Select, DatePicker, Input } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { fetchHotelById } from "../../redux/slice/hotelSlice";
import { LockOutlined, UnlockOutlined, HomeOutlined, RestOutlined } from "@ant-design/icons";
import CheckInModal from "../../components/CheckInModal";

const { RangePicker } = DatePicker;

const RoomLayout = () => {
  const { hotel, loading } = useSelector((state) => state.hotel);
  const dispatch = useDispatch();
  const [hotelId, setHotelId] = useState(null);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [roomStatus, setRoomStatus] = useState({});
  // const [lockInfo, setLockInfo] = useState({ start: null, end: null, reason: "" });
  const [modalCheckIn, setModalCheckIn] = useState({ open: false, room: null });

  useEffect(() => {
    const storedHotelId = localStorage.getItem("hotelId");
    if (storedHotelId) {
      setHotelId(storedHotelId);
    }
  }, []);

  useEffect(() => {
    if (hotelId) {
      dispatch(fetchHotelById(hotelId));
    }
  }, [dispatch, hotelId]);

  if (loading) return <p>Đang tải...</p>;
  if (!hotel || !hotel.floors) return <p>Không có dữ liệu khách sạn.</p>;

  const handleRoomClick = (room) => {
    setSelectedRoom(room);
  };

  const handleOpenCheckIn = (room) => {
    setModalCheckIn({ open: true, room });
  };

  return (
    <div>
      {hotel.floors.map((floor) => (
        <div key={floor.id} style={{ marginBottom: 20 }}>
          <h2>{floor.name}</h2>
          <Row gutter={[16, 16]}>
            {floor.rooms.map((room) => (
              <Col key={room.id} span={4}>
                <Popover
                  content={
                    selectedRoom?.id === room.id && (
                      <div style={{ width: 200 }}>
                        <Button
                          type="primary"
                          icon={<HomeOutlined />}
                          style={{ marginBottom: 10, width: "100%" }}
                          onClick={() => handleOpenCheckIn(room)}
                        >
                          Nhận phòng nhanh
                        </Button>
                        <Button
                          icon={<RestOutlined />}
                          style={{ marginBottom: 10, width: "100%" }}
                          onClick={() =>
                            setRoomStatus((prev) => ({
                              ...prev,
                              [selectedRoom.id]: prev[selectedRoom.id] === "dirty" ? "normal" : "dirty",
                            }))
                          }
                        >
                          {roomStatus[selectedRoom?.id] === "dirty" ? "Đã dọn dẹp" : "Cần dọn dẹp"}
                        </Button>
                      </div>
                    )
                  }
                  trigger="click"
                  open={selectedRoom?.id === room.id}
                  onVisibleChange={(visible) => !visible && setSelectedRoom(null)}
                >
                  <Card
                    title={`Phòng ${room.name}`}
                    style={{
                      backgroundColor:
                        roomStatus[room.id] === "locked"
                          ? "#D35400"
                          : roomStatus[room.id] === "dirty"
                          ? "#F1C40F"
                          : room.isOccupied
                          ? "#85C1E9"
                          : "#D5DBDB",
                      textAlign: "center",
                      cursor: "pointer",
                    }}
                    onClick={() => handleRoomClick(room)}
                  >
                    {roomStatus[room.id] === "locked" ? (
                      <>
                        <LockOutlined style={{ fontSize: 20, color: "white" }} /> <br />
                        <span style={{ color: "white" }}>Phòng bị khóa</span>
                      </>
                    ) : roomStatus[room.id] === "dirty" ? (
                      <>
                        <RestOutlined style={{ fontSize: 20, color: "black" }} /> <br />
                        <span>Cần dọn dẹp</span>
                      </>
                    ) : room.isOccupied ? (
                      `Khách: ${room.guestName}`
                    ) : (
                      "Phòng trống"
                    )}
                  </Card>
                </Popover>
              </Col>
            ))}
          </Row>
        </div>
      ))}
      
      {/* Check-in Modal */}
      <CheckInModal
        visible={modalCheckIn.open}
        onClose={() => setModalCheckIn({ open: false, room: null })}
        roomSelect={modalCheckIn.room}
      />
    </div>
  );
};

export default RoomLayout;
