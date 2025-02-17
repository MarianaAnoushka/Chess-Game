package src;

public class Pawn extends Piece {

    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int direction = color == PieceColor.WHITE ? -1 : 1;
        int rowDiff = ((newPosition.getRow() - position.getRow()) * direction);
        int colDiff = newPosition.getColumn() - position.getColumn();

        //forward move one square
        if(colDiff == 0 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            return true;
        }

        //first two-square move
        boolean isFirstPos = (color == PieceColor.WHITE && position.getRow() == 6) || (color == PieceColor.BLACK && position.getRow() == 1);
        if(colDiff == 0 && rowDiff == 2 && isFirstPos && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            int between = position.getRow() + direction;
            if(board[between][newPosition.getColumn()] == null) {
                return true;
            }
        }

        //capturing a piece diagonally
        if(Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null && board[newPosition.getRow()][newPosition.getColumn()].color != this.color) {
            return true;
        }
        return false;
    }
}
