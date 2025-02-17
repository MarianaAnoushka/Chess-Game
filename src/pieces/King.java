package src.pieces;

import src.Piece;
import src.Position;
import src.PieceColor;

public class King extends Piece {

    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {

        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());
  
        // Kings can move one square in any direction.
        boolean isOneSquare = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);
  
        if (!isOneSquare) {
            return false; // Move is not within one square.
        }

        //check if we will be capturing a piece
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if(destinationPiece == null) {
            return true; //there is no piece to capture and the move is valid
        }
        else return destinationPiece.getColor() != this.color;
    }
}
