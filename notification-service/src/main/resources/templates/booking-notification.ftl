${msg["booking.subject"]?replace("{0}", bookingId)}

${msg["booking.greeting"]}

${msg["booking.thank_you"]?replace("{0}", hotelName)}
${msg["booking.details_intro"]}

${msg["booking.info_title"]}
${msg["booking.id"]}: ${bookingId}
${msg["booking.dates"]}: ${checkInDate} – ${checkOutDate}
${msg["booking.room_number"]}: ${roomNumber}
${msg["booking.room_type"]}: ${roomTypeName}
${msg["booking.description"]}: ${roomDescription}
${msg["booking.price"]}: ${pricePerDay} USD

${msg["hotel.info_title"]}
${msg["hotel.name"]}: ${hotelName}
${msg["hotel.address"]}: ${hotelAddress}
${msg["hotel.phone"]}: ${hotelPhone}
${msg["hotel.email"]}: ${hotelEmail}
${msg["hotel.website"]}: ${hotelWebsite}

${msg["booking.contact_note"]}
${msg["booking.farewell"]}
${msg["booking.signature"]?replace("{0}", hotelName)}